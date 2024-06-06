package com.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.lang.NonNull;

import java.util.Objects;
//@EnableJpaRepositories
public abstract class BaseQueryDsl extends QuerydslRepositorySupport {
    public BaseQueryDsl(Class<?> domainClass) {
        super(domainClass);
    }

    @NonNull
    @Override
    public Querydsl getQuerydsl() {
        return Objects.requireNonNull(super.getQuerydsl());
    }

    @NonNull
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
