package com.service.shipping.service;

import com.service.shipping.request.ShippingRequest;
import com.service.shipping.response.ShippingResponse;

public interface ShippingService {

    ShippingResponse orderReceipt(ShippingRequest request);
}
