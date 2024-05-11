package com.service.employee.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
