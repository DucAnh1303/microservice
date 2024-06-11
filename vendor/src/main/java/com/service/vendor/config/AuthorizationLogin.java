package com.service.vendor.config;

import com.service.vendor.response.AuthResponse;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthorizationLogin implements AuditorAware<Long> {

    @NonNull
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(0L);
        }
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.of(0L);
        }

        AuthResponse user = (AuthResponse) authentication.getPrincipal();
        return Optional.of(user.getId());
    }
}
