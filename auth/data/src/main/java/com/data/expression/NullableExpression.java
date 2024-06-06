package com.data.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.util.StringUtils;

public class NullableExpression {

    private NullableExpression() {
        throw new UnsupportedOperationException();
    }

    public static BooleanExpression eq(final StringExpression expression, final String value) {
        return StringUtils.hasText(value) ? expression.eq(value) : null;
    }

    public static <T extends Enum<T>> BooleanExpression eq(final EnumExpression<T> expression, final T value) {
        return value == null ? null : expression.eq(value);
    }

    public static <T extends Number & Comparable<?>> BooleanExpression eq(
            final NumberExpression<T> expression, final T value) {
        return value == null ? null : expression.eq(value);
    }

    public static BooleanExpression like(final StringExpression expression, final String value) {
        return value == null ? null : expression.like("%" + value + "%");
    }

}
