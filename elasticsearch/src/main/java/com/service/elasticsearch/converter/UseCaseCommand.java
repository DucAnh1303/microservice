package com.service.elasticsearch.converter;

import com.service.elasticsearch.entitties.ElasProductEntity;
import com.service.elasticsearch.request.ProductRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UseCaseCommand {

    public static ElasProductEntity save(ProductRequest product) {
        return ElasProductEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
