package com.service.microservice.data.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface BiPredicate<T, U> {
	BooleanExpression[] to(final T first, U second);
}
