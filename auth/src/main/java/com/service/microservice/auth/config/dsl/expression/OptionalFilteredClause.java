package com.service.microservice.auth.config.dsl.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
public class OptionalFilteredClause<E> {

    private JPQLQuery<E> query;

    public <P> Condition<E, P> ifPresent(final Predicate<P> predicate) {
        return new Condition<>(query, predicate);
    }

    public <T, U> BiCondition<E, T, U> ifPresent(final BiPredicate<T, U> biPredicate) {
        return new BiCondition<>(query, biPredicate);
    }

    public static class Condition<E, P> {
        private final JPQLQuery<E> query;
        private final Predicate<P> predicate;

        public Condition(final JPQLQuery<E> query, final Predicate<P> predicate) {
            this.predicate = predicate;
            this.query = query;
        }

        public OptionalFilteredClause<E> where(
                final Function<Predicate<P>, BooleanExpression[]> mapper) {
            Optional.ofNullable(predicate).map(mapper).ifPresent(query::where);

            return new OptionalFilteredClause<>(query);
        }
    }

    public static class BiCondition<E, T, U> {
        private final JPQLQuery<E> query;
        private final BiPredicate<T, U> biPredicate;

        public BiCondition(final JPQLQuery<E> query, final BiPredicate<T, U> biPredicate) {
            this.biPredicate = biPredicate;
            this.query = query;
        }

        public OptionalFilteredClause<E> where(
                final Function<BiPredicate<T, U>, BooleanExpression[]> mapper) {
            Optional.ofNullable(biPredicate).map(mapper).ifPresent(query::where);
            return new OptionalFilteredClause<>(query);
        }
    }
}
