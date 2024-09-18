package com.service.elasticsearch.entitties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products")
@Getter
@Setter
@Builder
public class ElasProductEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
