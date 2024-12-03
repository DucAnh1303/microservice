package com.service.microservice.gateway.config.common;

import java.util.Set;

public class IpValidator {

    public static final Set<String> blockedIps = Set.of("192.168.1.100", "203.0.113.0");
}
