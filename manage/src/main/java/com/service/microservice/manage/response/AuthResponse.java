package com.service.microservice.manage.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
