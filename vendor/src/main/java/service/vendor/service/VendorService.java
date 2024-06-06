package service.vendor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.vendor.request.VendorRequest;
import service.vendor.response.VendorResponse;


public interface VendorService {

    Page<VendorResponse> getAll(Pageable pageable, String name);

    VendorResponse detail(Long id);

    VendorResponse addVendor(VendorRequest request);
}
