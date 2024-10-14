package com.service.microservice.manage.request.account;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountRequest {

    private Long id;
    private String name;
    private String email;
    private int age;
    private Date birthDay;
    private String address;
    private int position;
    private String description;
}
