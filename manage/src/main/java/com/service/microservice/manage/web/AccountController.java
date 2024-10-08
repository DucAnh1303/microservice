package com.service.microservice.manage.web;

import com.service.microservice.manage.common.ApiResponseGenerator;
import com.service.microservice.manage.common.BaseResponse;
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


    @PostMapping("/search")
    public BaseResponse<?> search(@RequestBody AccountSearch search) {
        return ApiResponseGenerator.success(accountService.findAll(search), HttpStatus.OK);
    }

    @PostMapping("/find-by/{id}")
    public BaseResponse<?> search(@PathVariable("id") Long id) {
        return ApiResponseGenerator.success(accountService.findById(id), HttpStatus.OK);
    }

}
