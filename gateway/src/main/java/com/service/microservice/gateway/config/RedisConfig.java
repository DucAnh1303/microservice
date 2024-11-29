package com.service.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // LettuceConnectionFactory is non-blocking and perfect for reactive applications
        return new LettuceConnectionFactory("localhost", 16379); // Redis host and port
    }

    // Create the ReactiveStringRedisTemplate bean
    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new ReactiveStringRedisTemplate((ReactiveRedisConnectionFactory) redisConnectionFactory);  // Reactive support
    }
}
