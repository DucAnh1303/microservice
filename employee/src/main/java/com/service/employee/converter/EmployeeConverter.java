package com.service.employee.converter;

import com.data.entity.EmployeeEntity;
import com.service.employee.domain.Employee;
import com.service.employee.domain.query.SearchEmployeeQuery;
import com.service.employee.predicate.EmployeePredicate;
import com.service.employee.response.EmployeeResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeConverter {

    public static EmployeePredicate to(final SearchEmployeeQuery query) {
        if (query == null){
            return new EmployeePredicate();
        }
        return EmployeePredicate.builder()
                .id(query.getId())
                .name(query.getName())
                .phone(query.getPhone())
                .address(query.getAddress())
                .build();
    }

    public static EmployeeResponse res(final Employee employee) {

        if (employee == null) {
            return null;
        }
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .build();
    }

    public static Employee from(final EmployeeEntity employee) {

        if (employee == null) {
            return null;
        }
        return Employee.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .build();
    }
}
