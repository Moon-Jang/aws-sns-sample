package com.example.snssample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;


@Configuration
public class SnsConfig extends AwsConfig {

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .credentialsProvider(() -> AwsBasicCredentials.create(awsAccessKey, awsSecretKey))
                .region(Region.of(region))
                .build();
    }

}
