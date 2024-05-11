package com.service.employee.domain.implement;

import com.data.BaseQueryDsl;
import com.data.entity.EmployeeEntity;
import com.data.entity.QEmployeeEntity;
import com.data.expression.OptionalFilteredClause;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import com.service.employee.predicate.EmployeePredicate;
import com.service.employee.repository.EmployeeDslRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDslImpl extends BaseQueryDsl implements EmployeeDslRepository {

    private static final QEmployeeEntity employee = QEmployeeEntity.employeeEntity;

    public EmployeeDslImpl() {
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
