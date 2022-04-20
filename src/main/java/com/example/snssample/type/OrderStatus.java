package com.example.snssample.type;

import java.util.Arrays;

public enum OrderStatus {
    CREATED,
    SHIPPING,
    COMPLETED,
    CANCELED;

    public static OrderStatus findByName(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("존재하지않는 의뢰상태입니다."));
    }
}
