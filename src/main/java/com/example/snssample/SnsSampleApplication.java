package com.example.snssample;

import com.example.snssample.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnsSampleApplication implements CommandLineRunner {

    @Autowired
    private SqsService sqsService;

    public static void main(String[] args) {
        SpringApplication.run(SnsSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sqsService.registerSqsListeners();
    }

}
