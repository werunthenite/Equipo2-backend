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
            "ASIATZZ7OPKK3WDCMF5T",
            "VRo8t8WHroRxAdXN0EwAfSIl4Sgg79IUgdSMv/5V",
            "FwoGZXIvYXdzEIL//////////wEaDGh8NlKX9nFroGSipCLFAXTMsZyH11+HDGjzzZhHoEpQP/DMXOzItkYdjqPDiNWH7VRxT6Tf4ftJIZhk3kL8KSsLxtBVx76FVK0IrOU6S1miJoKTi4lqvUJnTHtZKPGotW+Jx1LzwjohWSt2qT75SVGsVzivInEjhgL3/+TwKnqhy/OEGVMnYFTszyAcbhcDugk6zYK8UAf2rDMEDdiiYft7u/JBjs7wSLnavWIbCQ1TOH2G1xKi1I3mD3lnVwSOxhBXEI1CV/WkyCbNEpVGItscgL2hKMDt9JMGMi2KDgkaRClZmKzsAf6vAxAhkuIyHKRVJ/VoNVUt1S7cOY7rStfuyXV/YuDmNYc=");

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
