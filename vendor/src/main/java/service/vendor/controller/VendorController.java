package service.vendor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.vendor.aop.CacheKey;
import service.vendor.common.ApiResponse;
import service.vendor.aop.CacheData;
import service.vendor.aop.CacheEvict;
import service.vendor.request.VendorRequest;
import service.vendor.response.VendorResponse;
import service.vendor.service.VendorService;

@RequiredArgsConstructor
@RequestMapping("/vendor")
@RestController
public class VendorController {

    private final VendorService service;

    @GetMapping
    @CacheData(key = "vendor", time = 60)
    public ApiResponse<?> getAll(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable
                    pageable,
            @CacheKey @RequestParam(value = "name",defaultValue = "") String name) {
        Page<VendorResponse> data = service.getAll(pageable,name);
        return ApiResponse.res(data, HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getDetail(@PathVariable Long id) {
        return ApiResponse.res(service.detail(id), HttpStatus.OK.value());
    }

    @PostMapping
    @CacheEvict(key = "vendor")
    public ApiResponse<?> addData(@RequestBody VendorRequest request) {
        return ApiResponse.res(service.addVendor(request), HttpStatus.OK.value());
    }
}
