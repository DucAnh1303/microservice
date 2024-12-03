package com.service.microservice.auth.service.impl;

import com.service.microservice.auth.common.exception.InvalidAuthenticationError;
import com.service.microservice.auth.common.exception.InvalidError;
import com.service.microservice.auth.common.exception.JwtAuthenticationError;
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
import com.service.microservice.auth.request.PageBaseRequest;
import com.service.microservice.auth.response.AuthGenJwtResponse;
import com.service.microservice.auth.response.AuthResponse;
import com.service.microservice.auth.service.inter.AuthService;
import com.service.microservice.support.BasePage;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtFilter jwtFilter;

    private final AuthRepository authRepository;

    private final AuthControlRepository authControlRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BasePage<?> search(PageBaseRequest request) {
        Pageable pageable = BasePage.getPageable(request);
        Page<AuthEntity> page = authRepository.findAll(pageable);
        List<AuthResponse> responses = page.getContent().stream()
                .map(AuthResConverter::from)
                .toList();
        return new BasePage<>(responses, page);
    }

    @Override
    public AuthGenJwtResponse login(LoginAccount loginAccount) {
        AuthEntity auth = authRepository.findByEmail(loginAccount.getEmail()).orElseThrow(InvalidAuthenticationError::new);
        checkPassword(loginAccount.getPassword(), auth.getPassword());
        return getJwtFilter(auth);
    }

    @Override
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

    @Override
    public AuthResponse add(AccountRegister register) {
        if (!this.isValidEmail(register.getEmail().trim())) {
            throw new InvalidError(HttpStatus.BAD_REQUEST, "Email is not format !");
        }
        if (!register.getPassword().matches(register.getConfirmPassword())) {
            throw new InvalidError(HttpStatus.BAD_REQUEST, "Password and conform password is not matches !");
        }
        if (this.authRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new InvalidError(HttpStatus.BAD_REQUEST, "Email is exits!");
        }
        AuthEntity auth = authRepository.save(AuthResConverter.to(register));
        AuthResponse response = AuthResConverter.from(auth);
        authControlRepository.save(AuthControlConverter.create(response, null, null));
        return response;
    }


    private boolean isValidEmail(String email) {
        return Validator.isValidEmail(email);
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
