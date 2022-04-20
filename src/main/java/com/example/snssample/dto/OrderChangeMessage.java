package com.example.snssample.dto;

import com.example.snssample.type.OrderStatus;
import com.example.snssample.type.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
public class OrderChangeMessage extends SnsMessage {

    private final Long orderSrl;

    private final String groupSrl;

    private final OrderType orderType;

    private final OrderStatus orderStatus;

}
