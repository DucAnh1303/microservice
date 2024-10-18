package com.service.microservice.manage.response.converter;

import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.request.account.AccountCreate;
import com.service.microservice.manage.request.account.AccountPredicate;
import com.service.microservice.manage.request.account.AccountRequest;
import com.service.microservice.manage.request.account.AccountSearch;
import com.service.microservice.manage.response.AuthResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class AccountConverter {

    public static AccountEntity to(AccountCreate authResponse) {

        return AccountEntity.builder()
                .name(authResponse.getName())
                .email(authResponse.getEmail())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    public static AuthResponse from(AccountEntity data, String password) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdDateString = data.getCreatedDate().format(formatter);
        String updatedDateString = data.getUpdatedDate().format(formatter);

        return AuthResponse.builder()
                .id(data.getId())
                .name(data.getName())
                .email(data.getEmail())
                .password(password)
                .showPassword(password)
                .createdDate(createdDateString)
                .updatedDate(updatedDateString)
                .createdUser(data.getCreatedUser())
                .updatedUser(data.getUpdatedUser())
                .build();
    }

    public static AccountPredicate search(AccountSearch search) {

        return AccountPredicate.builder().id(search.getId())
                .name(search.getName())
                .email(search.getEmail())
                .build();
    }

    public static AccountEntity command(AccountEntity account, AccountRequest request) {

        return account.toBuilder()
                .id(request.getId())
                .age(request.getAge())
                .birthDay(request.getBirthDay())
                .address(request.getAddress())
                .position(request.getPosition())
                .description(request.getDescription())
                .build();
    }

    public static AccountEntity commandCreate(AccountCreate request) {

        return AccountEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
