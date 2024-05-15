package org.service.shipping.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ShipmentDeserializer<T> extends JsonDeserializer<T> {
    private final Class<T> type;

    public ShipmentDeserializer(Class<T> type) {
        this.type = type;
    }

}
