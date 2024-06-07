package com.service.vendor.service.impl;

import com.service.vendor.entity.VendorEntity;
import com.service.vendor.repository.VendorRepository;
import com.service.vendor.request.VendorRequest;
import com.service.vendor.request.converter.VendorRequestConverter;
import com.service.vendor.response.VendorResponse;
import com.service.vendor.response.converter.VendorResponseConverter;
import com.service.vendor.service.VendorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Page<VendorResponse> getAll(Pageable page, String name) {
        return vendorRepository.findAll(name,page).map(VendorResponseConverter::to);
    }

    @Override
    public VendorResponse detail(Long id) {
        return vendorRepository.findById(id).map(VendorResponseConverter::to).orElseThrow();
    }

    @Override
    @Transactional
    public VendorResponse addVendor(VendorRequest request) {
        VendorEntity data = vendorRepository.save(VendorRequestConverter.executed(request));
        return VendorResponseConverter.to(data);
    }
}
