package com.service.vendor.response.converter;

import com.data.entity.VendorEntity;
import com.service.vendor.response.VendorResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VendorResponseConverter {

    public VendorResponse to(VendorEntity data){
        return VendorResponse.builder()
                .id(data.getId())
                .phone(data.getPhone())
                .name(data.getName())
                .address(data.getAddress())
                .description(data.getDescription())
                .information(data.getInformation()).build();
    }
}
