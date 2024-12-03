package com.service.microservice.support;

import com.service.microservice.auth.request.PageBaseRequest;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
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

    public static Pageable getPageable(PageBaseRequest request) {
        List<Sort.Order> orders = new ArrayList<>();
        if (request != null) {
            if (request.getOrderPageList() != null) {
                for (PageBaseRequest.OrderPage orderPage : request.getOrderPageList()) {
                    if ("ASC".equals(orderPage.getOrderBy())) {
                        orders.add(Sort.Order.asc(orderPage.getField()));
                    } else if ("DESC".equals(orderPage.getOrderBy())) {
                        orders.add(Sort.Order.desc(orderPage.getField()));
                    }
                }
            }
        } else {
            request = new PageBaseRequest();
        }
        return PageRequest.of(BasePage.getPageNo(request.getPageNo()), request.getPageSize(), Sort.by(orders));
    }

    public static Pageable getPageable(Integer pageNo, Integer pageSize) {
        return PageRequest.of(BasePage.getPageNo(pageNo), pageSize);
    }
}
