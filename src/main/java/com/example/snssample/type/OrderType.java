package com.example.snssample.type;

import java.util.Arrays;

public enum OrderType {
    D2D,
    RETURN,
    WAREHOUSING;

    public static OrderType findByName(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("존재하지않는 의뢰타입입니다."));
    }
}
