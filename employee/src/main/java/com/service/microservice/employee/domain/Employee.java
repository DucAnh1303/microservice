package com.service.microservice.employee.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@With
@Builder(toBuilder = true)
public class Employee {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
