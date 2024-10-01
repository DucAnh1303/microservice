package com.service.microservice.employee.repository.emloyee.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.service.microservice.data.entity.QEmployeeEntity;
import com.service.microservice.data.expression.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.service.microservice.data.expression.NullableExpression.eq;
import static com.service.microservice.data.expression.NullableExpression.like;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeePredicate implements Predicate<QEmployeeEntity> {

    private Long id;
    private String name;
    private String phone;
    private String address;

    @Override
    public BooleanExpression[] to(QEmployeeEntity entity) {
        return new BooleanExpression[]{
                eq(entity.id, this.id),
                like(entity.name, this.name),
                like(entity.phone,this.phone),
                eq(entity.address,this.address)

        };
    }

}
