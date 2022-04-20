package com.example.snssample.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@ConfigurationProperties("aws.sns")
@RequiredArgsConstructor
public class SnsTopics {

    private final List<Topic> topics;

    @Getter
    @Setter
    public static class Topic {
        private String name;
        private String arn;
        private List<Subscriber> subscribers;
    }

    @Getter
    @Setter
    public static class Subscriber {
        private String name;
        private String listener;
    }

}
