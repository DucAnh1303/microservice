package com.service.auth.service;

import com.service.auth.converter.AuthGenJwtConverter;
import com.service.auth.converter.AuthResConverter;
import com.service.auth.repository.AuthRepository;
import com.service.auth.request.AuthRequest;
import com.service.auth.response.AuthGenJwtResponse;
import com.service.auth.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final AuthRepository authRepository;

    public AuthGenJwtResponse login(AuthRequest authRequest) {
        AuthResponse response = authRepository.findByName(authRequest.getName())
                .map(AuthResConverter::from).orElseThrow(ResolutionException::new);
        String token = jwtService.generate(response, "ACCESS");
        String refreshToken = jwtService.generate(response, "REFRESH");
        return AuthGenJwtConverter.from(token, refreshToken);
    }
}
