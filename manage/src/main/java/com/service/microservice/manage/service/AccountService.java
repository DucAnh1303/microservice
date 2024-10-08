package com.service.microservice.manage.service;

import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.request.account.AccountRequest;
import com.service.microservice.manage.request.account.AccountSearch;
import org.springframework.data.domain.Page;

public interface AccountService {

    Page<AccountEntity> findAll(AccountSearch search);

    AccountEntity findById(Long id);

    AccountEntity updateUser(AccountRequest request);

}
