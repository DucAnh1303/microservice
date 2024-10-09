package com.service.microservice.manage.config.dsl.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface Predicate<E> {
    BooleanExpression[] to(final E entity);
}
