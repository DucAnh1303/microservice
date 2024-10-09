package com.service.microservice.manage.web;

import com.service.microservice.manage.common.ApiResponseGenerator;
import com.service.microservice.manage.common.BaseResponse;
import com.service.microservice.manage.request.account.AccountRequest;
import com.service.microservice.manage.request.account.AccountSearch;
import com.service.microservice.manage.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/search")
    public BaseResponse<?> search(@RequestBody AccountSearch search) {
        return ApiResponseGenerator.success(accountService.findAll(search), HttpStatus.OK);
    }

    @GetMapping("/find-by/{idAuth}")
    public BaseResponse<?> search(@PathVariable("idAuth") Long idAuth) {
        return ApiResponseGenerator.success(accountService.findById(idAuth), HttpStatus.OK);
    }

    @PutMapping("/execute")
    public BaseResponse<?> update(@RequestBody AccountRequest request) {
        return ApiResponseGenerator.success(accountService.updateUser(request), HttpStatus.OK);
    }

}
