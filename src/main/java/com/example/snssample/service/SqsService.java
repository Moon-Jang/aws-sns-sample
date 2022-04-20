package com.example.snssample.service;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.example.snssample.config.SnsTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import java.lang.reflect.InvocationTargetException;

@Service
@RequiredArgsConstructor
public class SqsService {

    private final SnsTopics snsTopics;
    private final SQSConnectionFactory sqsConnectionFactory;

    public void registerSqsListeners() {
        snsTopics.getTopics()
                .stream()
                .flatMap(topic -> topic.getSubscribers().stream())
                .forEach(subscriber -> setAsyncListener(subscriber.getName(), subscriber.getListener()));
    }

    private void setAsyncListener(String queueName, String listenerClassName) {
        Session session = null;

        try {
            SQSConnection connection = sqsConnectionFactory.createConnection();

            validateQueue(connection, queueName);

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(createListener(listenerClassName));
            connection.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);

            throw new RuntimeException(e);
        }
    }

    private MessageListener createListener(String listenerClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (MessageListener) Class.forName(listenerClassName)
                .getConstructor()
                .newInstance();
    }

    private void validateQueue(SQSConnection connection, String queueName) throws Exception {
        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if (!client.queueExists(queueName)) {
            throw new RuntimeException("존재하지않는 큐입니다.");
        }
    }

}
