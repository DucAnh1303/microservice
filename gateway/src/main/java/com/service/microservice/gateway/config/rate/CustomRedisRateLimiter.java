//package com.service.microservice.gateway.config.rate;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
//import org.springframework.cloud.gateway.support.ConfigurationService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
//import org.springframework.data.redis.core.script.RedisScript;
//import org.springframework.web.bind.annotation.RequestMapping;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.List;
//
//public class CustomRedisRateLimiter extends RedisRateLimiter {
//
//    private final ReactiveStringRedisTemplate redisTemplate;
//
//
//    @Override
//    public Mono<Response> isAllowed(String routeId, String id) {
//        return super.isAllowed(routeId, id).doOnNext(response -> {
//            if (response.isAllowed()) {
//                // Cài đặt TTL tùy chỉnh
//                String redisKey = "request_rate_limiter." + id;
//                redisTemplate.expire(redisKey, Duration.ofSeconds(30)) // TTL là 30 giây
//                        .subscribe();
//            }
//        });
//    }
//
//}
