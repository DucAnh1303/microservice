package com.service.microservice.auth.service;

import com.service.microservice.auth.common.exception.InvalidAuthenticationError;
import com.service.microservice.auth.common.exception.JwtAuthenticationError;
import com.service.microservice.auth.common.exception.InvalidError;
import com.service.microservice.auth.common.exception.TokenExpiredException;
import com.service.microservice.auth.common.until.TypeJwt;
import com.service.microservice.auth.common.validate.Validator;
import com.service.microservice.auth.config.filter.JwtFilter;
import com.service.microservice.auth.converter.AuthControlConverter;
import com.service.microservice.auth.converter.AuthGenJwtConverter;
import com.service.microservice.auth.converter.AuthResConverter;
import com.service.microservice.auth.entities.AuthControlEntity;
import com.service.microservice.auth.entities.AuthEntity;
import com.service.microservice.auth.repository.AuthControlRepository;
import com.service.microservice.auth.repository.AuthRepository;
import com.service.microservice.auth.request.AccountRegister;
import com.service.microservice.auth.request.LoginAccount;
import com.service.microservice.auth.response.AuthGenJwtResponse;
import com.service.microservice.auth.response.AuthResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final JwtFilter jwtFilter;

    private final AuthRepository authRepository;

    private final AuthControlRepository authControlRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public List<AuthResponse> search() {
        return authRepository.findAll().stream().map(AuthResConverter::from).toList();
    }

    public AuthGenJwtResponse login(LoginAccount loginAccount) {
        AuthEntity auth = authRepository.findByEmail(loginAccount.getEmail()).orElseThrow(InvalidAuthenticationError::new);
        checkPassword(loginAccount.getPassword(), auth.getPassword());
        return getJwtFilter(auth);
    }

    public AuthGenJwtResponse refreshToken(String refreshToken) {
        Claims claims = jwtFilter.getClaimsFromToken(refreshToken);
        String id = claims.getSubject();
        boolean checkRefreshToken = jwtFilter.isTokenExpired(refreshToken);
        if (checkRefreshToken) {
            throw new TokenExpiredException("Token expired at: " + claims.getExpiration());
        }
        AuthEntity auth = authRepository.findById(Long.valueOf(id)).orElseThrow(InvalidAuthenticationError::new);
        Optional<AuthControlEntity> authControl = authControlRepository.findByAuthId(auth.getId());
        checkRefreshToken(refreshToken, authControl.get().getRefreshToken());
        return getJwtFilter(auth);
    }

    public AuthResponse add(AccountRegister register) {
        if (isValidEmail(register.getEmail())) {
            throw new InvalidError("Email is not format !");
        }
        if (register.getPassword().matches(register.getConfirmPassword())) {
            throw new InvalidError("Password and conform password is not matches !");
        }
        AuthEntity auth = authRepository.save(AuthResConverter.to(register));
        AuthResponse response = AuthResConverter.from(auth);
        authControlRepository.save(AuthControlConverter.create(response, null, null));
        return response;
    }

    private static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Validator.EMAIL_PATTERN.matcher(email).matches();
    }

    private void checkPassword(String password, String conformPassword) {
        if (!passwordEncoder.matches(password, conformPassword)) {
            throw new InvalidError(HttpStatus.BAD_REQUEST, "Password invalid");
        }
    }

    private void checkRefreshToken(String refreshToken, String newRefreshToken) {
        if (!refreshToken.matches(newRefreshToken)) {
            throw new JwtAuthenticationError("RefreshToken expired");
        }
    }

    private AuthGenJwtResponse getJwtFilter(AuthEntity auth) {
        AuthResponse response = AuthResConverter.from(auth);
        String token = jwtFilter.generate(response, TypeJwt.ACCESS.name());
        String refreshToken = jwtFilter.generate(response, TypeJwt.REFRESH.name());
        Optional<AuthControlEntity> authControl = authControlRepository.findByAuthId(response.getId());
        if (authControl.isEmpty()) {
            authControlRepository.save(AuthControlConverter.create(response, token, refreshToken));
        } else {
            authControlRepository.save(AuthControlConverter.update(authControl.get(), token, refreshToken));
        }
        return AuthGenJwtConverter.from(token, refreshToken);
    }

}
