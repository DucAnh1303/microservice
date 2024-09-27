package com.service.elasticsearch.repository.elastic;

import com.service.elasticsearch.sqlindex.ElasProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ElasticsearchRepository<ElasProductEntity, String> {

}
