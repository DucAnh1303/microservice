package com.service.microservice.employee.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmployeeRequest {

    private Long id;
    private String name;
    private String address;
    private String phone;
}
