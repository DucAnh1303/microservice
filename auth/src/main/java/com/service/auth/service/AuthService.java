package com.service.auth.service;

import com.service.auth.converter.AuthGenJwtConverter;
import com.service.auth.converter.AuthResConverter;
import com.service.auth.exception.TokenExpiredException;
import com.service.auth.repository.AuthRepository;
import com.service.auth.request.AuthRequest;
import com.service.auth.response.AuthGenJwtResponse;
import com.service.auth.response.AuthResponse;
import com.service.auth.until.TypeJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final AuthRepository authRepository;

    public AuthGenJwtResponse login(AuthRequest authRequest) {

        return getJwt(authRequest.getName());
    }

    public AuthGenJwtResponse refreshToken(String refreshToken) {

        if (jwtService.isTokenExpired(refreshToken)) {
            throw new TokenExpiredException();
        }
        String userName = jwtService.getToken(refreshToken);
        return getJwt(userName);
    }

    private AuthGenJwtResponse getJwt(String userName) {

        AuthResponse response = authRepository.findByName(userName)
                .map(AuthResConverter::from).orElseThrow(ResolutionException::new);
        String token = jwtService.generate(response, TypeJwt.ACCESS.name());
        String newRefreshToken = jwtService.generate(response, TypeJwt.REFRESH.name());
        return AuthGenJwtConverter.from(token, newRefreshToken);
    }
}
