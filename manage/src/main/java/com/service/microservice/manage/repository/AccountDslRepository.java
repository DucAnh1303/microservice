package com.service.microservice.manage.repository;

import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.request.account.AccountPredicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountDslRepository {

    Page<AccountEntity> findAll(AccountPredicate predicate, Pageable pageable);
}
