package com.example.snssample.service;

import com.example.snssample.dto.SnsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SnsService {

    private final SnsClient snsClient;
    private final ObjectMapper objectMapper;

    public String publish(Map<String, Object> body) {
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        Map<Object, Object> attributes = (Map<Object, Object>) body.get("attributes");

        attributes.keySet()
                .stream()
                .forEach(key -> messageAttributes.put(key.toString(), MessageAttributeValue.builder()
                        .dataType("String")
                        .stringValue(attributes.get(key.toString()).toString())
                        .build()));

        final PublishRequest publishRequest = PublishRequest.builder()
                .topicArn("arn:aws:sns:ap-northeast-2:590394026048:OrderChangeExample")
                .subject("SNS-sample publish test")
                .message(body.get("message").toString())
                .messageAttributes(messageAttributes)
                .build();

        PublishResponse publishResponse = snsClient.publish(publishRequest);

        return publishResponse.messageId();
    }

    public String publish(SnsMessage snsMessage, Map<String, MessageAttributeValue> messageAttributes) throws JsonProcessingException {
//        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
//        //Map<Object, Object> attributes = (Map<Object, Object>) body.get("attributes");
//
//        attributes.keySet()
//                .stream()
//                .forEach(key -> messageAttributes.put(key.toString(), MessageAttributeValue.builder()
//                        .dataType("String")
//                        .stringValue(attributes.get(key.toString()).toString())
//                        .build()));

        final PublishRequest publishRequest = PublishRequest.builder()
                .topicArn("arn:aws:sns:ap-northeast-2:590394026048:OrderChangeExample")
                //.subject("SNS-sample publish test")
                .message(objectMapper.writeValueAsString(snsMessage))
                .messageAttributes(messageAttributes)
                .build();

        PublishResponse publishResponse = snsClient.publish(publishRequest);

        return publishResponse.messageId();
    }

}
