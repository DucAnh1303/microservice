package com.service.microservice.employee.repository.emloyee;

import com.service.microservice.data.entity.EmployeeEntity;
import com.service.microservice.employee.repository.emloyee.predicate.EmployeePredicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeDslRepository {

    Page<EmployeeEntity> search(EmployeePredicate predicate, Pageable pageable);
}
