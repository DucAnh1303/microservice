package com.service.microservice.auth.common.validate;

import java.util.regex.Pattern;

public class Validator {

    private static final String CUSTOM_EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(gmail\\.com|yahoo\\.com|hotmail\\.com)$";

    public static final Pattern EMAIL_PATTERN = Pattern.compile(CUSTOM_EMAIL_REGEX);
}
