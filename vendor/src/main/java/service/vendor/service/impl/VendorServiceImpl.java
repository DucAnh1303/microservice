package service.vendor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import service.vendor.entity.VendorEntity;
import service.vendor.repository.VendorRepository;
import service.vendor.service.VendorService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    public final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Object getAll(String key) {
        Object cachedVendors = redisTemplate.opsForValue().get(key);
        if (cachedVendors != null) {
            return cachedVendors;
        }
        List<VendorEntity> vendors = vendorRepository.findAll();
        redisTemplate.opsForValue().set(key, vendors, 60, TimeUnit.SECONDS);
        return vendors;
    }
}
