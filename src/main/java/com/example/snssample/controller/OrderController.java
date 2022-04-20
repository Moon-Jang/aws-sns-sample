package com.example.snssample.controller;

import com.example.snssample.aspect.annotation.OrderChangeEvent;
import com.example.snssample.service.OrderService;
import com.example.snssample.service.SnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/groups/{groupSrl}/orders/{orderSrl}")
    @OrderChangeEvent
    public boolean update(@PathVariable String groupSrl, @PathVariable Long orderSrl) {
        return orderService.update(groupSrl, orderSrl);
    }

}
