package com.service.microservice.employee.web;

import com.service.microservice.employee.common.SnackRequest;
import com.service.microservice.employee.converter.EmployeeConverter;
import com.service.microservice.employee.converter.EmployeeConverterRequest;
import com.service.microservice.employee.domain.Employee;
import com.service.microservice.employee.domain.usecase.SearchEmployeeUseCase;
import com.service.microservice.employee.request.EmployeeRequest;
import com.service.microservice.employee.response.EmployeeResponse;
import com.service.microservice.support.ApiResponseGenerator;
import com.service.microservice.support.BaseResponse;
import com.service.microservice.support.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final SearchEmployeeUseCase searchUseCase;


    @GetMapping()
    public BaseResponse<Page<EmployeeResponse>> browse(
            @SnackRequest final EmployeeRequest request,
            @SnackRequest final Pageable pageable) {

        org.springframework.data.domain.Page<Employee> data =
                searchUseCase.execute(EmployeeConverterRequest.to(request, pageable));
        return ApiResponseGenerator.success(data.map(EmployeeConverter::res), HttpStatus.OK);
    }

}
