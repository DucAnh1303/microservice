package com.service.vendor.service;

import com.service.vendor.request.VendorRequest;
import com.service.vendor.response.VendorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VendorService {

    Page<VendorResponse> getAll(Pageable pageable, String name);

    VendorResponse detail(Long id);

    VendorResponse addVendor(VendorRequest request);
}
