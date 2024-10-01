package com.service.microservice.auth.converter;

import com.service.microservice.auth.response.AuthResponse;
import com.service.microservice.data.entity.AuthEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthResConverter {

    public static AuthResponse from(AuthEntity auth) {
        return AuthResponse.builder()
                .id(auth.getId())
                .name(auth.getName())
                .build();
    }
}
