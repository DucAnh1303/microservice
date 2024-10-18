package com.service.microservice.auth.converter;

import com.service.microservice.auth.entities.AuthControlEntity;
import com.service.microservice.auth.response.AuthResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Optional;

@UtilityClass
public class AuthControlConverter {

    public static AuthControlEntity create(AuthResponse response, String token, String refreshToken) {
        return AuthControlEntity.builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .token(token)
                .refreshToken(refreshToken)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .createdUser(response.getId())
                .updatedUser(response.getId())
                .build();
    }

    public static AuthControlEntity update(AuthControlEntity response, String token, String refreshToken) {
        return response.toBuilder()
                .token(token)
                .refreshToken(refreshToken)
                .updatedDate(LocalDateTime.now())
                .updatedUser(response.getId())
                .build();
    }

}
