package com.service.microservice.manage.service;

import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.request.account.AccountRequest;
import com.service.microservice.manage.request.account.AccountSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    Page<AccountEntity> findAll(AccountSearch search);

    AccountEntity findById(Long id);

    void updateUser(AccountRequest request);

}
