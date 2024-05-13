package com.service.employee.web;

import com.service.employee.common.SnackRequest;
import com.service.employee.converter.EmployeeConverter;
import com.service.employee.converter.EmployeeConverterRequest;
import com.service.employee.domain.Employee;
import com.service.employee.domain.usecase.SearchEmployeeUseCase;
import com.service.employee.request.EmployeeRequest;
import com.service.employee.response.EmployeeResponse;
import com.service.support.ApiResponse;
import com.service.support.ApiResponseGenerator;
import com.service.support.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final SearchEmployeeUseCase searchUseCase;


    @GetMapping
    public ApiResponse<Page<EmployeeResponse>> browse(
            @SnackRequest final EmployeeRequest request,
            final @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable
                    pageable) {

        org.springframework.data.domain.Page<Employee> data =
                searchUseCase.execute(EmployeeConverterRequest.to(request, pageable));
        return ApiResponseGenerator.success(data.map(EmployeeConverter::res), HttpStatus.OK);
    }
}
