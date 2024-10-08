package com.service.microservice.manage.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageForm {
    private int page;
    private int pageSize;

    public int getPage() {
        return this.page > 0 ? (this.page - 1) : 0;
    }
}
