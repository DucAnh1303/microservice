package com.service.microservice.auth.domain;

import com.service.microservice.auth.config.filter.JwtFilter;
import com.service.microservice.auth.config.message.ProducerMessage;
import com.service.microservice.auth.converter.AuthGenJwtConverter;
import com.service.microservice.auth.converter.AuthResConverter;
import com.service.microservice.auth.entities.AuthEntity;
import com.service.microservice.auth.exception.TokenExpiredException;
import com.service.microservice.auth.exception.UserNameOrPasswordInValidException;
import com.service.microservice.auth.repository.AuthRepository;
import com.service.microservice.auth.request.AuthCreate;
import com.service.microservice.auth.request.AuthRequest;
import com.service.microservice.auth.response.AuthGenJwtResponse;
import com.service.microservice.auth.response.AuthResponse;
import com.service.microservice.auth.until.TypeJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final JwtFilter jwtFilter;

    private final AuthRepository authRepository;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final ProducerMessage producerMessage;


    public AuthGenJwtResponse login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
            return getJwtFilter(authRequest.getName());
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        AuthResponse response = authRepository.findByName(userDetails.getUsername())
                .map(AuthResConverter::from).orElseThrow(ResolutionException::new);
        String token = jwtFilter.generate(response, TypeJwt.ACCESS.name());
        String newRefreshToken = jwtFilter.generate(response, TypeJwt.REFRESH.name());
        return AuthGenJwtConverter.from(token, newRefreshToken);
    }

    public AuthResponse create(AuthCreate create) {
        if (!isValidate(create)) {
            throw new RuntimeException("UserName or email is exits");
        }
        AuthEntity auth = authRepository.save(AuthResConverter.to(create));
        producerMessage.ReceiveOrderNotification(auth);
        return AuthResConverter.from(auth);
    }

    private boolean isValidate(AuthCreate create) {
        return authRepository.findByEmailAndName(create.getEmail(), create.getName()).isEmpty();
    }
}
