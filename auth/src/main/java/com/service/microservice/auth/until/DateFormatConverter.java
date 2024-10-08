package com.service.microservice.auth.until;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatConverter {

    public static LocalDateTime convertStringToDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(dateStr, formatter);
        } catch (Exception e) {
            return null;
        }

    }
}
