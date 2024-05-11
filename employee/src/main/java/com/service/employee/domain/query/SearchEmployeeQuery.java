package com.service.employee.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchEmployeeQuery  {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Pageable pageable;
}
