package com.ceylon_fusion.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOriginGetAllProductDetailsResponseDTO {
    private String stateLocation;
    private String stateMapLink;
    private String partOfPlant;
    private String originDescription;
    private String factoryName;
    private String factoryAddress;
    private String factoryMapLink;
    private String demoVideoLink;
}
