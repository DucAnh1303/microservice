package service.vendor.response.converter;

import lombok.experimental.UtilityClass;
import service.vendor.entity.VendorEntity;
import service.vendor.response.VendorResponse;

@UtilityClass
public class VendorResponseConverter {

    public VendorResponse to(VendorEntity data){
        return VendorResponse.builder()
                .id(data.getId())
                .phone(data.getPhone())
                .name(data.getName())
                .address(data.getAddress())
                .description(data.getDescription())
                .information(data.getInformation()).build();
    }
}
