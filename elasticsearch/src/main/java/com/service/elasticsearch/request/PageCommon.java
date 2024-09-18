package com.service.elasticsearch.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCommon {
    private int pageNumber;
    private int pageSize;

    public int getPage() {
        return this.pageNumber > 0 ? (this.pageNumber - 1) : 0;
    }

}
