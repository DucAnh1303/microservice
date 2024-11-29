package com.service.microservice.auth.web;

import com.service.microservice.auth.request.AccountRegister;
import com.service.microservice.auth.service.AuthServiceImpl;
import com.service.microservice.auth.request.LoginAccount;
import com.service.microservice.auth.response.AuthGenJwtResponse;
import com.service.microservice.support.ApiResponseGenerator;
import com.service.microservice.support.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @GetMapping("/search")
    public BaseResponse<?> search() {
        return ApiResponseGenerator.success(authServiceImpl.search(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public BaseResponse<?> login(@Valid @RequestBody LoginAccount request) {
        return ApiResponseGenerator.success(authServiceImpl.login(request), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public BaseResponse<?> refreshToken(@RequestParam("refresh") String refreshToken) {
        return ApiResponseGenerator.success(authServiceImpl.refreshToken(refreshToken), HttpStatus.OK);
    }

//    @PutMapping("/execute")
//    public BaseResponse<?> update(@RequestBody AccountRequest request) {
//        AuthEntity account = accountService.updateUser(request);
//        return ApiResponseGenerator.success(account, HttpStatus.OK);
//    }

    @PostMapping("/execute")
    public BaseResponse<?> create(@Valid @RequestBody AccountRegister register) {
        return ApiResponseGenerator.success(authServiceImpl.add(register), HttpStatus.CREATED);
    }

}
