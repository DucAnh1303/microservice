package service.vendor.request.converter;

import lombok.experimental.UtilityClass;
import service.vendor.entity.VendorEntity;
import service.vendor.request.VendorRequest;

@UtilityClass
public class VendorRequestConverter {

    public static VendorEntity executed(VendorRequest request){
        return VendorEntity.builder()
                .address(request.getAddress())
                .phone(request.getPhone())
                .description(request.getDescription())
                .name(request.getName())
                .information(request.getInformation()).build();
    }
}
