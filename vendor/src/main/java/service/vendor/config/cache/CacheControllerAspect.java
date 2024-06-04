package service.vendor.config.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class CacheControllerAspect implements Serializable {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(cacheController)")
    public Object cacheMethod(ProceedingJoinPoint joinPoint, CacheController cacheController) throws Throwable {
        String key = cacheController.key();
        long time = cacheController.time();

        Object cachedValue = redisTemplate.opsForValue().get(key);
        if (cachedValue != null) {
            return cachedValue;
        }

        Object result = joinPoint.proceed();

        redisTemplate.opsForValue().set(key, result, time, TimeUnit.SECONDS);

        return result;
    }

    @Around("@annotation(cacheEvictController)")
    public Object evictCache(ProceedingJoinPoint joinPoint, CacheEvictController cacheEvictController) throws Throwable {
        String key = cacheEvictController.key();

        Object result = joinPoint.proceed();

        redisTemplate.delete(key);

        return result;
    }
}
