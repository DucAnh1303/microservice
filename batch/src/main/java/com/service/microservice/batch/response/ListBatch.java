package com.service.microservice.batch.response;

import lombok.Data;

import java.util.List;

@Data
public class ListBatch {
    List<BatchResponse> responseList;
}
