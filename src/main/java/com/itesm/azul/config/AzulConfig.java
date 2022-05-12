package com.itesm.azul.config;

/**
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

/**
 * @author Molina
 *
 */
@Configuration
public class AzulConfig {
/*
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "localhost")).withCredentials(
                        new AWSStaticCredentialsProvider(new BasicAWSCredentials("rarkct", "c8lg7e"))
                )
                .build();
    }
*/
    /*
    @Bean
    public DynamoDbClient getDynamoDbClient() {
        //Ver documentación
        //https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/CodeSamples.Java.html#CodeSamples.Java.Credentials
        DynamoDbClient client = DynamoDbClient.builder()
                .endpointOverride(URI.create("https://ec2.eu-west-1.amazonaws.com"))
                // The region is meaningless for local DynamoDb but required for client builder validation
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("9buzbv", "437n80m")))
                .build();
        return client;
        //return DynamoDbClient.builder().region(Region.US_EAST_1).credentialsProvider(credentialsProvider).build();
    }
    */
@Bean
public DynamoDbClient getDynamoDbClient() {
    AwsSessionCredentials awsCreds = AwsSessionCredentials.create(
            "ASIATZZ7OPKK4WQAJYVY",
            "M4vzs1B9fj6rgJn3OEnHRJGVaNRoxLh8jchSSa9A",
            "FwoGZXIvYXdzEIj//////////wEaDGeWOd5EH7DFCh5TaiLFAWY5csmS90GyazGG1hyTowvlRiq+ZAmqPodNkU3nRQJIYozjOoFEWGLnfZ9Ie2p5vzMYv8t98ctjIbX/K2wlcEXyuRSEH8cWARQZqaIDnAAVKmDxGo9ur5J7BnAnbR882MHf+ans3fBQvKHQUaHKS/f8+dkFXUxMMSiOFg97kGTWYSoILxlMP4qYtm3RiQrkGSqXE7TevE7jPtz3yepm1KYXYvd6qqkC35N+AzNTx8lkB379BU73RKE/hB7UPFiWaUv7ZxqhKKiZ9pMGMi0Po/C0RalLd5FmTWU5MeSm4MnESFTw4lqkyoVWimlLOocbhnDM9BZg5ThYwMA=");

    AwsCredentialsProvider credentialsProvider =
            DefaultCredentialsProvider.builder()
                    .profileName("default")
                    .build();

    return DynamoDbClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();
}

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(getDynamoDbClient())
                .build();
    }
}
