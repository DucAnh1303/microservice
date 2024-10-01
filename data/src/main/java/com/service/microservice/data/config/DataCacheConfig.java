package com.service.microservice.data.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
        "com.service.data.config",
})
@EnableCaching
@Configuration
public class DataCacheConfig {
}
