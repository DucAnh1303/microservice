package com.service.microservice.manage.config.dsl;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Propagation;

import java.util.Objects;

@DomainTransactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class BaseQueryDslRepository extends QuerydslRepositorySupport {
    public BaseQueryDslRepository(Class<?> domainClass) {
        super(domainClass);
    }

    @Nonnull
    @Override
    public Querydsl getQuerydsl() {
        return Objects.requireNonNull(super.getQuerydsl());
    }

    @Nonnull
    @Override
    public EntityManager getEntityManager() {
        return Objects.requireNonNull(super.getEntityManager());
    }

    @Override
    @PersistenceContext
    public void setEntityManager(@NonNull EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }
}
