package com.service.microservice.employee.repository.emloyee;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import com.service.microservice.data.BaseQueryDslRepository;
import com.service.microservice.data.entity.EmployeeEntity;
import com.service.microservice.data.entity.QEmployeeEntity;
import com.service.microservice.data.expression.OptionalFilteredClause;
import com.service.microservice.employee.repository.emloyee.predicate.EmployeePredicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDslRepositoryImpl extends BaseQueryDslRepository implements EmployeeDslRepository {

    private static final QEmployeeEntity employee = QEmployeeEntity.employeeEntity;

    public EmployeeDslRepositoryImpl() {
        super(EmployeeEntity.class);
    }

    @Override
    public Page<EmployeeEntity> search(EmployeePredicate predicate, Pageable pageable) {
        JPQLQuery<EmployeeEntity> query =
                getQuerydsl().applyPagination(pageable, this.createFindQuery(employee, predicate));

        return PageableExecutionUtils.getPage(
                query.fetch(),
                pageable,
                () -> this.createFindQuery(employee.count(), predicate).fetchFirst());
    }

    private <T> JPQLQuery<T> createFindQuery(final Expression<T> selectExpression, final EmployeePredicate predicate) {
        JPQLQuery<T> query = getQuerydsl().createQuery().select(selectExpression).from(employee);

        new OptionalFilteredClause<>(query).ifPresent(predicate).where(e -> e.to(employee));

        return query;
    }

}
