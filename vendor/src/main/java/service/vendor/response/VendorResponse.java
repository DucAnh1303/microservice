package service.vendor.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
    private String information;
}
