package org.service.shipping.domain.usecase;

import com.data.entity.ShippingEntity;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.service.shipping.domain.command.ShippingCommand;
import org.service.shipping.domain.converter.ShippingConverter;
import org.service.shipping.domain.repository.ShippingDslRepository;
import org.service.shipping.domain.repository.ShippingRepository;
import org.service.shipping.web.ShippingResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserShippingCommandService {
    private final ShippingDslRepository dslRepository;
    private final ShippingRepository repository;

    @Transactional
    public void execute(ShippingResponse response) {
        if (response != null ){
            ShippingEntity shipping = new ShippingEntity();
            shipping.setOrderId(ShippingConverter.to(response).getId());
            shipping.setTimeOrderInDay(String.valueOf(LocalDateTime.now()));
            shipping.setTimeShippingInDay(String.valueOf(LocalDateTime.now()));
            shipping.setCreatedDate(LocalDateTime.now());
            repository.save(shipping);
        }else {
            System.out.println("Shipping is not received");
        }

    }

}
