package com.service.microservice.employee.domain.usecase;

import com.service.microservice.employee.converter.EmployeeConverter;
import com.service.microservice.employee.domain.Employee;
import com.service.microservice.employee.domain.query.SearchEmployeeQuery;
import com.service.microservice.employee.repository.emloyee.predicate.EmployeePredicate;
import com.service.microservice.employee.repository.emloyee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchEmployeeUseCase {

    private final EmployeeRepository repository;

    public Page<Employee> execute(final SearchEmployeeQuery query) {

        EmployeePredicate predicate = EmployeeConverter.to(query);

        return repository.search(predicate, query.getPageable()).map(EmployeeConverter::from);
    }

}
