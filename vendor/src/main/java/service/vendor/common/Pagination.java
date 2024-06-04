package service.vendor.common;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
public class Pagination<T> {
    private long page;
    private long size;
    private long total;
    private Object body;


    public Pagination(Page<T> page) {
        final Pageable pageable = page.getPageable();
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.total = page.getTotalPages();
        this.body = page.getContent();
    }
}
