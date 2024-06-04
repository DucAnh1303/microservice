package service.vendor.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import service.vendor.common.Pagination;
import service.vendor.entity.VendorEntity;
import service.vendor.repository.VendorRepository;
import service.vendor.request.VendorRequest;
import service.vendor.request.converter.VendorRequestConverter;
import service.vendor.response.VendorResponse;
import service.vendor.response.converter.VendorResponseConverter;
import service.vendor.service.VendorService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Page<VendorResponse> getAll(Pageable page) {
        return vendorRepository.findAll(page).map(VendorResponseConverter::to);
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
