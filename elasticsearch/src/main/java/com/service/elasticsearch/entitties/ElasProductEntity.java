package com.service.elasticsearch.entitties;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products")
public class ElasProductEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
