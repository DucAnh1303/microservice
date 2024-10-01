package com.service.microservice.auth.web;

import com.service.microservice.auth.request.AuthRequest;
import com.service.microservice.auth.response.AuthGenJwtResponse;
import com.service.microservice.auth.domain.usecase.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthGenJwtResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthGenJwtResponse> refreshToken(@RequestParam("refresh") String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}
