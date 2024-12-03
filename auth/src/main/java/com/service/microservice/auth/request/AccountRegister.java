package com.service.microservice.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRegister {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "Password and conform password is not matches !")
    private String confirmPassword;
    private String accessRole;
}
