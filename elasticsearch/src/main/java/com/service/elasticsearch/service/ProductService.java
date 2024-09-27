package com.service.elasticsearch.service;

import com.service.elasticsearch.common.BaseResponse;
import com.service.elasticsearch.converter.UseCaseCommand;
import com.service.elasticsearch.sqlindex.ElasProductEntity;
import com.service.elasticsearch.repository.elastic.ProductRepository;
import com.service.elasticsearch.request.PageCommon;
import com.service.elasticsearch.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public BaseResponse<?> getAllProducts(PageCommon pageCommon) {
        Pageable pageable = PageRequest.of(pageCommon.getPage(), pageCommon.getPageSize());
        Page<ElasProductEntity> products = productRepository.findAll(pageable);
        return new BaseResponse<>(products);
    }

    public ElasProductEntity save(ProductRequest request) {
        return productRepository.save(UseCaseCommand.save(request));
    }
}
