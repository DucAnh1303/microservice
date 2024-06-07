package com.service.vendor.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {

    private String address;
    private String information;
    private String name;
    private String description;
    private String phone;
}
