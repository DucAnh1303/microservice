package com.service.elasticsearch.repository;

import com.service.elasticsearch.entitties.ElasProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<ElasProductEntity, String> {

    List<ElasProductEntity> findByName(String name);
}
