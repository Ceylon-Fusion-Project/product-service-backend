package com.ceylon_fusion.product_service.dto.request;

import com.ceylon_fusion.product_service.entity.enums.CategoryType;
import com.ceylon_fusion.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductUpdateDetailsRequestDTO {
    private String productName;
    private String productDescription;
    private Double sellingPrice;
    private CategoryType categoryType;
    private MeasuringUnitType measuringUnitType;
    private List<String> productImageURLs;
    private boolean productActiveState;
    private Integer productOrigin;
}
