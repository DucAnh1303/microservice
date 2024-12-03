package com.service.microservice.auth.common.validate;

import java.util.Set;

public class Validator {

    private static final Set<String> ALLOWED_DOMAINS = Set.of("gmail.com", "yahoo.com", "hotmail.com");

    public static boolean isValidEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }
        return ALLOWED_DOMAINS.contains(parts[1]);
    }
}
