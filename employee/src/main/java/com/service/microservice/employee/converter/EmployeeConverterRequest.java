package com.service.microservice.employee.converter;

import com.service.microservice.employee.domain.query.SearchEmployeeQuery;
import com.service.microservice.employee.request.EmployeeRequest;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Pageable;

@UtilityClass
public class EmployeeConverterRequest {

    public static SearchEmployeeQuery to(final EmployeeRequest request, final Pageable pageable){
        if (request == null){
            return null;
        }

        return SearchEmployeeQuery.builder()
                .id(request.getId())
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .pageable(pageable)
                .build();
    }
}
