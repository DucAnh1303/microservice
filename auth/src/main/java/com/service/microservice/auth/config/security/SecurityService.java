package com.service.microservice.auth.config.security;

import com.service.microservice.auth.entities.AuthEntity;
import com.service.microservice.auth.entities.RoleEntity;
import com.service.microservice.auth.repository.AuthControlRepository;
import com.service.microservice.auth.repository.AuthRepository;
import com.service.microservice.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final AuthControlRepository authControlRepository;
    private final AuthRepository authRepository;

    public AuthEntity findByAuth(Long id) {
        AuthEntity auth = authRepository.findById(id).orElse(new AuthEntity());
        return auth;
    }

    public String getCurrentToken(Long id) {
        return authControlRepository.findByAuthId(id).get().getToken();
    }
}
