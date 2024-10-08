package com.service.microservice.auth.web;

import com.service.microservice.auth.domain.AuthServiceImpl;
import com.service.microservice.auth.request.AuthCreate;
import com.service.microservice.auth.request.AuthRequest;
import com.service.microservice.auth.response.AuthGenJwtResponse;
import com.service.microservice.auth.response.AuthResponse;
import com.service.microservice.support.ApiResponseGenerator;
import com.service.microservice.support.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<AuthGenJwtResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authServiceImpl.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthGenJwtResponse> refreshToken(@RequestParam("refresh") String refreshToken) {
        return ResponseEntity.ok(authServiceImpl.refreshToken(refreshToken));
    }

    @PostMapping("/create")
    public BaseResponse<AuthResponse> create(@Valid @RequestBody AuthCreate request) {
        AuthResponse authResponse = authServiceImpl.create(request);
        return ApiResponseGenerator.success(authResponse, HttpStatus.CREATED);
    }
}
