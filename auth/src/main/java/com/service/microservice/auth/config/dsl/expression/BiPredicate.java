package com.service.microservice.auth.config.dsl.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface BiPredicate<T, U> {
    BooleanExpression[] to(final T first, U second);
}
