package com.example.snssample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class AwsConfig {

    @Value("${aws.credentials.accessKey}")
    protected String awsAccessKey;

    @Value("${aws.credentials.secretKey}")
    protected String awsSecretKey;

    @Value("${aws.region}")
    protected String region;

}
