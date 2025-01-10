package com.ceylon_fusion.product_service.dto.response;

import com.ceylon_fusion.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductGetAllDetailsResponseDTO {
    //Product Details
    private Integer productID;
    private String productCode;
    private String productName;
    private String productDescription;
    private Double sellingPrice;
    private double productQuantity;
    private MeasuringUnitType measuringUnitType;
    private List<String> productImageURLs;
    private Double productRatingValue;

    //Certification Details
    private List<CertificationGetAllProductDetailsResponseDTO> certificationList;

    //Product Rating Details
    private List<ProductRatingGetAllProductDetailsResponseDTO> productRatingList;

    //Product Origin Details
    private ProductOriginGetAllProductDetailsResponseDTO productOrigin;
}
