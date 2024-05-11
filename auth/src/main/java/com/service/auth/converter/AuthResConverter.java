package com.service.auth.converter;

import com.data.entity.AuthEntity;
import com.service.auth.response.AuthResponse;
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
