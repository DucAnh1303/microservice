package com.service.microservice.support;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class BasePage<D> {

    private int pageNo;
    private int pageSize;
    private int totalPages;
    private List<D> response;

    public BasePage(List<D> response, Page<?> page) {
        this.pageNo = page.getPageable().getPageNumber() + 1;
        this.pageSize = page.getPageable().getPageSize();
        this.totalPages = page.getTotalPages();
        this.response = response;
    }

    public static Integer getPageNo(Integer pageNo) {
        return (pageNo != null && pageNo > 0) ? pageNo - 1 : 0;
    }
}
