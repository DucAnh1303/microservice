package org.service.order.web.converter;

import com.data.entity.OrderEntity;
import lombok.experimental.UtilityClass;
import org.service.order.web.response.OrderResponse;

@UtilityClass
public class OrderResponseConverter {

    public static OrderResponse to(final OrderEntity entity){
        return OrderResponse.builder()
                .id(entity.id)
                .name(entity.getName())
                .description(entity.getDescription())
                .number(entity.getNumber()).build();
    }
}
