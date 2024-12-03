package com.service.microservice.auth.web;

import com.service.microservice.auth.request.AccountRegister;
import com.service.microservice.auth.request.LoginAccount;
import com.service.microservice.auth.request.PageBaseRequest;
import com.service.microservice.auth.service.inter.AuthService;
import com.service.microservice.support.ApiResponseGenerator;
import com.service.microservice.support.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/search")
    public BaseResponse<?> search(@RequestBody(required = false) PageBaseRequest request) {
        return ApiResponseGenerator.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), authService.search(request));
    }

    @PostMapping("/login")
    public BaseResponse<?> login(@Valid @RequestBody LoginAccount request) {
        return ApiResponseGenerator.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), authService.login(request));
    }

    @PostMapping("/refresh-token")
    public BaseResponse<?> refreshToken(@RequestParam("refresh") String refreshToken) {
        return ApiResponseGenerator.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), authService.refreshToken(refreshToken));
    }

    @PostMapping("/execute")
    public BaseResponse<?> create(@Valid @RequestBody AccountRegister register) {
        return ApiResponseGenerator.success(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), authService.add(register));
    }

}
