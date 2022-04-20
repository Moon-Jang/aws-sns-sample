package com.example.snssample.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class OrderTrackingListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("OrderTrackingListener Received: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
