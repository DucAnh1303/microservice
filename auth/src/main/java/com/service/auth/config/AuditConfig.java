package com.service.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "currentAuditor")
public class AuditConfig {

    @Bean
    public AuditorAware<Long> currentAuditor() {
        return new AuthorizationLogin();
    }
}
