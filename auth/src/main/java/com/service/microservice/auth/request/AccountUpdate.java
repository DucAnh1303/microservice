package com.service.microservice.auth.request;

import lombok.Data;

@Data
public class AccountUpdate {

    private String name;
    private String email;
}
