package com.service.microservice.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAccount {
    @NotBlank(message = "Password is not empty")
    private String password;
    @NotBlank(message = "Email is not empty")
    private String email;
}
