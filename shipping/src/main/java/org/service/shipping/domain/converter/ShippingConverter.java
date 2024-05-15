package org.service.shipping.domain.converter;

import lombok.experimental.UtilityClass;
import org.service.shipping.domain.command.ShippingCommand;
import org.service.shipping.web.ShippingResponse;

@UtilityClass
public class ShippingConverter {

    public static ShippingCommand to(ShippingResponse response){
        return ShippingCommand.builder().id(response.getId()).build();
    }
}
