package com.ceylon_fusion.product_service.dto.response;

import com.ceylon_fusion.product_service.entity.enums.CategoryType;
import com.ceylon_fusion.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductGetAllResponseDTO {
    private Integer productID;
    private boolean productActiveState;
    private String productCode;
    private String productName;
    private String productDescription;
    private Double sellingPrice;
    private String productImageURL;
    private Double productRatingValue;
    private CategoryType categoryType;
    private List<String> productImageURLs;
    private MeasuringUnitType measuringUnitType;

    //Product Origin Details
    private ProductOriginGetAllProductDetailsResponseDTO productOrigin;
}
