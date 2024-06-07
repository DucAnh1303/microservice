package com.service.vendor.request.converter;

import com.service.vendor.entity.VendorEntity;
import com.service.vendor.request.VendorRequest;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class VendorRequestConverter {

    public static VendorEntity executed(VendorRequest request) {
        VendorEntity add = new VendorEntity();
        add.setAddress(request.getAddress());
        add.setPhone(request.getPhone());
        add.setDescription(request.getDescription());
        add.setName(request.getName());
        add.setInformation(request.getInformation());
        add.setCreatedDate(LocalDateTime.now());
        return add;
    }
}
