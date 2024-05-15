package org.service.order.domain.convert;

import lombok.experimental.UtilityClass;
import org.service.order.domain.command.OrderCommand;
import org.service.order.web.request.OrderRequest;

@UtilityClass
public class OrderCommandConverter {

    public static OrderCommand to(OrderRequest request){
        return OrderCommand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .number(request.getNumber()).build();
    }
}
