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
import org.springframework.web.bind.annotation.RestController;
import service.vendor.common.ApiResponse;
import service.vendor.common.ApiResponseGenerator;
import service.vendor.common.Pagination;
import service.vendor.config.cache.CacheController;
import service.vendor.request.VendorRequest;
import service.vendor.response.VendorResponse;
import service.vendor.service.VendorService;

@RequiredArgsConstructor
@RequestMapping("/vendor")
@RestController
public class VendorController {

    private final VendorService service;

    @GetMapping
    @CacheController(key = "vendor",time = 60)
    public ApiResponse<Pagination<VendorResponse>> getAll(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable
                    pageable) {
        Page<VendorResponse> data = service.getAll(pageable);
        return ApiResponseGenerator.res(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ApiResponse<VendorResponse> getDetail(@PathVariable Long id) {
        return ApiResponseGenerator.res(service.detail(id), HttpStatus.OK);
    }

    @PostMapping
    public ApiResponse<VendorResponse> addData(@RequestBody VendorRequest request) {
        return ApiResponseGenerator.res(service.addVendor(request), HttpStatus.OK);
    }
}
