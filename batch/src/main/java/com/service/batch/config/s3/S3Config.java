package com.service.batch.config.s3;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${spring.cloud.aws.s3.region}")
    private String region;

//    @Value("${spring.cloud.aws.credentials.access-key}")
//    private String accessKey;
//
//    @Value("${spring.cloud.aws.credentials.secret-key}")
//    private String secretKey;


    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.builder().build())
                .region(Region.of(region))
                .build();
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }
}
