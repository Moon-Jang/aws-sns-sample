package com.example.snssample.aspect;

import com.example.snssample.dto.OrderChangeMessage;
import com.example.snssample.service.SnsService;
import com.example.snssample.type.OrderStatus;
import com.example.snssample.type.OrderType;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
@EnableAsync
public class OrderChangeAspect {

    private final SnsService snsService;
    private final HttpServletRequest request;

    @After("@annotation(com.example.snssample.aspect.annotation.OrderChangeEvent)")
    public void orderChangeInterface() throws Throwable {
        Map<Object, Object> pathParameters = (Map<Object, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        final String groupSrl = pathParameters.get("groupSrl").toString();
        final Long orderSrl = Long.valueOf(pathParameters.get("orderSrl").toString());
        OrderType orderType = OrderType.findByName(request.getParameter("orderType"));
        OrderStatus orderStatus = OrderStatus.findByName(request.getParameter("orderStatus"));

        OrderChangeMessage orderChangeMessage = OrderChangeMessage.builder()
                .orderSrl(orderSrl)
                .orderStatus(orderStatus)
                .groupSrl(groupSrl)
                .orderType(orderType)
                .build();

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("orderType", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue(orderType.name())
                .build());
        messageAttributes.put("orderStatus", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue(orderStatus.name())
                .build());

        messageAttributes.put("groupSrl", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue(groupSrl)
                .build());

        snsService.publish(orderChangeMessage, messageAttributes);
    }
}
