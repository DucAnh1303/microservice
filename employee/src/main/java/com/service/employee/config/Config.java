package com.service.employee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@EntityScan("com.data.entity")
@ComponentScan("com.data")
public class Config {
}
