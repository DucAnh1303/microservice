package com.service.auth.service;

import com.service.auth.converter.AuthGenJwtConverter;
import com.service.auth.converter.AuthResConverter;
import com.service.auth.exception.TokenExpiredException;
import com.service.auth.exception.UserNameOrPasswordInValidException;
import com.service.auth.repository.AuthRepository;
import com.service.auth.request.AuthRequest;
import com.service.auth.response.AuthGenJwtResponse;
import com.service.auth.response.AuthResponse;
import com.service.auth.until.TypeJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtFilter jwtFilter;

    private final AuthRepository authRepository;

    private final AuthenticationManager authenticationManager;


    public AuthGenJwtResponse login(AuthRequest authRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
            return getJwtFilter(authentication.getName());
        } catch (Exception e) {
            throw new UserNameOrPasswordInValidException("Incorrect username or password! ");
        }
    }

    public AuthGenJwtResponse refreshToken(String refreshToken) {
        if (jwtFilter.isTokenExpired(refreshToken)) {
            Date dateExpiration = jwtFilter.getExpirationDateFromToken(refreshToken);
            throw new TokenExpiredException("Token is expiration time: " + dateExpiration + ", please login again! ");
        }
        String userName = jwtFilter.getToken(refreshToken);
        return getJwtFilter(userName);
    }

    private AuthGenJwtResponse getJwtFilter(String userName) {

        AuthResponse response = authRepository.findByName(userName)
                .map(AuthResConverter::from).orElseThrow(ResolutionException::new);
        String token = jwtFilter.generate(response, TypeJwt.ACCESS.name());
        String newRefreshToken = jwtFilter.generate(response, TypeJwt.REFRESH.name());
        return AuthGenJwtConverter.from(token, newRefreshToken);
    }
}
