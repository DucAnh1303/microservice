package com.service.vendor.config;

import com.service.vendor.service.impl.AuthorizationLogin;
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
