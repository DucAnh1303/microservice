package com.service.microservice.auth.converter;

import com.service.microservice.auth.entities.AuthEntity;
import com.service.microservice.auth.request.AuthCreate;
import com.service.microservice.auth.response.AuthResponse;
import com.service.microservice.auth.until.DateFormatConverter;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class AuthResConverter {


    public static AuthResponse from(AuthEntity auth) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdDateString = auth.getCreatedDate().format(formatter);
        String updatedDateString = auth.getUpdatedDate().format(formatter);

        return AuthResponse.builder()
                .id(auth.getId())
                .name(auth.getName())
                .email(auth.getEmail())
                .createdDate(createdDateString)
                .updatedDate(updatedDateString)
                .createdUser(auth.getCreatedUser())
                .updatedUser(auth.getUpdatedUser())
                .build();
    }

    public static AuthEntity to(AuthResponse res) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        LocalDateTime convertCreateStrtoDate = DateFormatConverter.convertStringToDate(res.getCreatedDate());
        LocalDateTime convertUpdateStrtoDate = DateFormatConverter.convertStringToDate(res.getUpdatedDate());

        return AuthEntity.builder()
                .id(res.getId())
                .name(res.getName())
                .email(res.getEmail())
                .password(passwordEncoder.encode(res.getPassword()))
                .showPassword(res.getShowPassword())
                .createdDate(convertCreateStrtoDate)
                .updatedDate(convertUpdateStrtoDate)
                .createdUser(res.getCreatedUser())
                .updatedUser(res.getUpdatedUser())
                .build();
    }

    public static AuthEntity to(AuthCreate create) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return AuthEntity.builder()
                .name(create.getName())
                .email(create.getEmail())
                .password(passwordEncoder.encode(create.getPassword()))
                .showPassword(create.getPassword())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
