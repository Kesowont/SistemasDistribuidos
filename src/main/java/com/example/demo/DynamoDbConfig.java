package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfig {

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(software.amazon.awssdk.regions.Region.US_EAST_2)
                .build(); // Usa credenciales automáticamente desde el entorno (IAM Role)
    }
}