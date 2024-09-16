package com.service.auth.controller;

import com.service.auth.request.AuthRequest;
import com.service.auth.response.AuthGenJwtResponse;
import com.service.auth.service.AuthService;
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
