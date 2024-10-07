package com.service.microservice.batch.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchExcel {

    private String id;
    private String name;
    private String address;
    private String number;
    private String date;
    private String email;
}
