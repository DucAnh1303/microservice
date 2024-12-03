package com.service.microservice.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBaseRequest {

    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private List<OrderPage> orderPageList = Collections.emptyList();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderPage {
        private String field;
        private String orderBy;
    }
}
