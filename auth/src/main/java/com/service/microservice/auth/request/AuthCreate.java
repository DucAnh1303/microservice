package com.service.microservice.auth.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class AuthCreate {

    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
