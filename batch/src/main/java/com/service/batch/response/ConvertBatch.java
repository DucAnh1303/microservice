package com.service.batch.response;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertBatch {

    public static BatchResponse convertData(BatchExcel batchExcel) {
        return BatchResponse
                .builder()
                .id(batchExcel.getId())
                .name(batchExcel.getName())
                .address(batchExcel.getAddress())
                .number(batchExcel.getNumber())
                .email(batchExcel.getEmail()).build();
    }
}
