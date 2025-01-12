package com.ceylon_fusion.product_service.dto.request;

import com.ceylon_fusion.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSaveRequestDTO {
    private String productCode;
    private String productName;
    private String productDescription;
    private Double sellingPrice;
    private double productQuantity;
    private MeasuringUnitType measuringUnitType;
    private List<String> productImageURLs;
    private Integer productOrigin;
}
