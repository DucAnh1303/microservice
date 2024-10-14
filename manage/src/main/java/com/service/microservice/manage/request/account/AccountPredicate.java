package com.service.microservice.manage.request.account;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.service.microservice.manage.config.dsl.expression.Predicate;
import com.service.microservice.manage.entity.account.QAccountEntity;
import lombok.*;

import static com.service.microservice.manage.config.dsl.expression.NullableExpression.eq;
import static com.service.microservice.manage.config.dsl.expression.NullableExpression.like;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPredicate implements Predicate<QAccountEntity> {
    private Long id;
    private String name;
    private String email;

    @Override
    public BooleanExpression[] to(QAccountEntity entity) {
        return new BooleanExpression[]{
                eq(entity.id, id),
                like(entity.name, name),
                like(entity.email, email)
        };
    }
}
