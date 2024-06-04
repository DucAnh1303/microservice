package service.vendor.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
public class Pagination<T> {
    private Object body;
    private long page;
    private long size;
    private long total;

    public Pagination(Page<T> page){
        final Pageable pageable = page.getPageable();
        this.body = page.getContent();
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.total = page.getTotalPages();
    }
}
