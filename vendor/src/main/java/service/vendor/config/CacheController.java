package service.vendor.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public  abstract class CacheController {
    private String key;
    private Long time;
}
