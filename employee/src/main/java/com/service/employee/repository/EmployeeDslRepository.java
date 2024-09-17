package com.service.employee.repository;

import com.data.entity.EmployeeEntity;
import com.service.employee.predicate.EmployeePredicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeDslRepository {

    Page<EmployeeEntity> search(EmployeePredicate predicate, Pageable pageable);
}
