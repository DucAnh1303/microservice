package com.service.employee.domain.usecase;

import com.service.employee.converter.EmployeeConverter;
import com.service.employee.domain.Employee;
import com.service.employee.domain.query.SearchEmployeeQuery;
import com.service.employee.predicate.EmployeePredicate;
import com.service.employee.repository.EmployeeDslRepository;
import com.service.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchEmployeeUseCase {

    private final EmployeeDslRepository repository;

    public Page<Employee> execute(final SearchEmployeeQuery query) {

        EmployeePredicate predicate = EmployeeConverter.to(query);

        return repository.search(predicate, query.getPageable()).map(EmployeeConverter::from);
    }

}
