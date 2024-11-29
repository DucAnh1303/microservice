package com.service.microservice.auth.response;

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
    private String role;
    private String createdDate;
    private String updatedDate;
    private Long createdUser;
    private Long updatedUser;
}
