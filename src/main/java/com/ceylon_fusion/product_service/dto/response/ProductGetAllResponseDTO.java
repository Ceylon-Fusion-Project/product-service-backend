package com.ceylon_fusion.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductGetAllResponseDTO {
    private Integer productID;
    private String productName;
    private String productDescription;
    private Double sellingPrice;
    private String productImageURL;
    private Double productRatingValue;
}
