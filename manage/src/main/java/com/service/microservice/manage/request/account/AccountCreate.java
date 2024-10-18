package com.service.microservice.manage.request.account;

import lombok.Getter;

@Getter
public class AccountCreate {
    private String name;
    private String email;
    private String password;
}
