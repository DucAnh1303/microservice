package org.service.order.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@Component
@EntityScan("com.data.entity")
public class ApplyConnectDB {
}
