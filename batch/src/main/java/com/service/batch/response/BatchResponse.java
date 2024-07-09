package com.service.batch.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BatchResponse {
    private String id;
    private String name;
    private String address;
    private String number;
    private String date;
    private String email;
}
