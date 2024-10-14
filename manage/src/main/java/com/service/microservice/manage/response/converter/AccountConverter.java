package com.service.microservice.manage.response.converter;

import com.service.microservice.manage.entity.account.AccountEntity;
import com.service.microservice.manage.request.account.AccountPredicate;
import com.service.microservice.manage.request.account.AccountRequest;
import com.service.microservice.manage.request.account.AccountSearch;
import com.service.microservice.manage.response.AuthResponse;
import com.service.microservice.manage.utils.DateFormatConverter;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class AccountConverter {

    public static AccountEntity to(AuthResponse authResponse) {

        LocalDateTime createdDate = DateFormatConverter.convertStringToDate(authResponse.getCreatedDate());
        LocalDateTime updatedDate = DateFormatConverter.convertStringToDate(authResponse.getUpdatedDate());

        return AccountEntity.builder()
                .id(authResponse.getId())
                .name(authResponse.getName())
                .email(authResponse.getEmail())
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdUser(authResponse.getCreatedUser())
                .updatedUser(authResponse.getUpdatedUser())
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
}
