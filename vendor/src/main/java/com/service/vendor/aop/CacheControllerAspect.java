package com.service.vendor.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class CacheControllerAspect implements Serializable {


    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;

    @Around("@annotation(cacheData)")
    public Object cacheMethod(ProceedingJoinPoint joinPoint, CacheData cacheData) throws Throwable {
        String key = cacheData.key();
        long time = cacheData.time();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        List<String> keyParameters = getParameters(joinPoint.getArgs(), method.getParameterAnnotations());
        String cacheKey = generate(key, keyParameters);
        try {

            ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
            String source = opsForValue.get(cacheKey);

            if (Objects.nonNull(source)) {
                return objectMapper.readValue(source, methodSignature.getReturnType());
            }

            Object result = joinPoint.proceed();

            redisTemplate.opsForValue().set(cacheKey, objectMapper.writeValueAsString(result), time, TimeUnit.SECONDS);

            return result;
        } catch (final RedisException | JsonProcessingException redisException) {
            log.error("[ControllerCacheableAspect Error]", redisException);
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(cacheEvict)")
    public Object evictCache(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
        String key = cacheEvict.key();

        Object result = joinPoint.proceed();

        redisTemplate.delete(key);

        return result;
    }

    public static String generate(
            final String key, final List<String> keys) {
        StringBuilder cacheKeyBuilder = new StringBuilder().append(key);

        if (!CollectionUtils.isEmpty(keys)) {
            cacheKeyBuilder.append(":").append(String.join(":", keys));
        }

        return cacheKeyBuilder.toString();
    }

    public static List<String> getParameters(final Object[] args, final Annotation[][] annotations) {
        List<String> keys = new ArrayList<>();

        for (int index = 0; index < annotations.length; index++) {
            boolean match =
                    Arrays.stream(annotations[index])
                            .anyMatch(annotation -> annotation instanceof CacheKey);
            if (match) {
                keys.add(args[index].toString());
            }
        }

        return keys;
    }
}
