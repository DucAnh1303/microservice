package com.service.microservice.manage.service;

import com.service.microservice.manage.config.message.producer.ProducerMessage;
import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.exceptionhandle.ExecuteException;
import com.service.microservice.manage.repository.AccountRepository;
import com.service.microservice.manage.request.account.AccountCreate;
import com.service.microservice.manage.request.account.AccountRequest;
import com.service.microservice.manage.request.account.AccountSearch;
import com.service.microservice.manage.response.AuthResponse;
import com.service.microservice.manage.response.converter.AccountConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ProducerMessage producerMessage;

    @Override
    public Page<AccountEntity> findAll(AccountSearch search) {

        Pageable pageable = PageRequest.of(search.getPage(), search.getPageSize(), Sort.Direction.DESC, "id");

        return accountRepository.findAll(AccountConverter.search(search), pageable);
    }

    @Override
    public AccountEntity findById(Long id) {

        return accountRepository.findById(id).orElseThrow(ResolutionException::new);
    }

    @Override
    public AccountEntity updateUser(AccountRequest request) {

        AccountEntity account = accountRepository.findById(request.getId())
                .orElseThrow(ResolutionException::new);

        return accountRepository.save(AccountConverter.command(account, request));
    }

    @Override
    public AccountEntity create(AccountCreate create) {

        if (accountRepository.findByEmailOrName(create.getEmail(), create.getName()).isPresent()) {
            throw new ExecuteException("UserName or email is exits");
        }

        AccountEntity auth = accountRepository.save(AccountConverter.to(create));
        producerMessage.send(auth, create.getPassword());
        return auth;
    }

}
