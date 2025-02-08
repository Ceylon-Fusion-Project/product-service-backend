package com.ceylon_fusion.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOriginSaveAndUpdateRequestDTO {
    private String stateLocation;
    private String stateMapLink;
    private String partOfPlant;
    private String originDescription;
    private String factoryName;
    private String factoryAddress;
    private String factoryMapLink;
    private String demoVideoLink;
    private String originCode;
}
