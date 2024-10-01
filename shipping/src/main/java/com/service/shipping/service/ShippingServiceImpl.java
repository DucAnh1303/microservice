package com.service.shipping.service;

import com.service.microservice.data.entity.ShippingEntity;
import com.service.shipping.repository.ShippingRepository;
import com.service.shipping.request.ShippingRequest;
import com.service.shipping.request.ShippingRequestConverter;
import com.service.shipping.response.ShippingResponse;
import com.service.shipping.response.ShippingResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingResponse orderReceipt(ShippingRequest request) {
        ShippingEntity save = shippingRepository.save(ShippingRequestConverter.receive(request));
        return ShippingResponseConverter.response(save);
    }


}
