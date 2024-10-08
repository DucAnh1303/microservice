package com.service.microservice.auth.repository;

import com.service.microservice.auth.config.dsl.BaseQueryDslRepository;
import com.service.microservice.auth.entities.AuthEntity;

public class AuthDslRepositoryImpl extends BaseQueryDslRepository implements AuthDslRepository {

    public AuthDslRepositoryImpl() {
        super(AuthEntity.class);
    }
}
