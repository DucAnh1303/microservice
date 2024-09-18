package com.service.elasticsearch.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private long totalElement;
    private List<T> data;

    public BaseResponse(Page<T> page) {
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPage = page.getTotalPages();
        this.totalElement = page.getTotalElements();
        this.data = page.getContent();
    }
}
