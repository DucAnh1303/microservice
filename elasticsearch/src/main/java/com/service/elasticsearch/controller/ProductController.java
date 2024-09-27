package com.service.elasticsearch.controller;

import com.service.elasticsearch.request.PageCommon;
import com.service.elasticsearch.request.ProductRequest;
import com.service.elasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(@RequestBody PageCommon pageCommon) {
        return ResponseEntity.ok(productService.getAllProducts(pageCommon));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.save(request));
    }

}
