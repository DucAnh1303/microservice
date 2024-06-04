package service.vendor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.vendor.request.VendorRequest;
import service.vendor.response.VendorResponse;

import java.util.List;

public interface VendorService {

    Page<VendorResponse> getAll(Pageable pageable);
    VendorResponse detail(Long id);

    VendorResponse addVendor(VendorRequest request);
}
