package com.service.auth.converter;

import com.service.auth.response.AuthGenJwtResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthGenJwtConverter {

    public static AuthGenJwtResponse from(final String token, final String refreshToken) {
        return AuthGenJwtResponse.builder().token(token).refreshToken(refreshToken).build();
    }
}
