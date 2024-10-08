package com.service.microservice.manage.request.account;

import com.service.microservice.manage.common.PageForm;
import lombok.Getter;

@Getter
public class AccountSearch extends PageForm {

    private Long id;
    private String name;
    private String email;
}
