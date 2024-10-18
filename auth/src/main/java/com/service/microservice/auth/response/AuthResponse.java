package com.service.microservice.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String showPassword;
    private String createdDate;
    private String updatedDate;
    private Long createdUser;
    private Long updatedUser;
}
