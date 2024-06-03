package service.vendor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.vendor.service.VendorService;

@RequiredArgsConstructor
@RequestMapping("/vendor")
@RestController
public class VendorController {

    private final VendorService service;

    @GetMapping
    @CacheC
    public Object getAll() {
        return service.getAll("vendor");
    }
}
