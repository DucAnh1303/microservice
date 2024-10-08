package com.service.microservice.manage.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import com.service.microservice.manage.config.dsl.BaseQueryDslRepository;
import com.service.microservice.manage.config.dsl.expression.OptionalFilteredClause;
import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.entity.account.QAccountEntity;
import com.service.microservice.manage.request.account.AccountPredicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

public class AccountDslRepositoryImpl extends BaseQueryDslRepository implements AccountDslRepository {
    private static final QAccountEntity account = QAccountEntity.accountEntity;

    public AccountDslRepositoryImpl() {
        super(AccountEntity.class);
    }

    @Override
    public Page<AccountEntity> findAll(AccountPredicate predicate, Pageable pageable) {
        JPQLQuery<AccountEntity> query =
                getQuerydsl().applyPagination(pageable, this.createFindQuery(account, predicate));

        return PageableExecutionUtils.getPage(
                query.fetch(),
                pageable,
                () -> this.createFindQuery(account.count(), predicate).fetchFirst());
    }

    private <T> JPQLQuery<T> createFindQuery(final Expression<T> selectExpression, final AccountPredicate predicate) {
        JPQLQuery<T> query = getQuerydsl().createQuery().select(selectExpression).from(account);

        new OptionalFilteredClause<>(query).ifPresent(predicate).where(e -> e.to(account));

        return query;
    }

}
