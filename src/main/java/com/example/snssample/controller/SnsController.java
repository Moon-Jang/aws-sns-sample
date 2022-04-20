package com.example.snssample.controller;

import com.example.snssample.service.SnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SnsController {

    private final SnsService snsService;

    @PostMapping("/publish")
    public String publish(@RequestBody Map<String, Object> body) {
        return snsService.publish(body);
    }

}
