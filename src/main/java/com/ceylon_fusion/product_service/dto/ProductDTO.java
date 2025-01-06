package com.ceylon_fusion.product_service.dto;

import com.ceylon_fusion.product_service.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private Integer productID;
    private String productCode;
    private String productName;
    private String productDescription;
    private Double sellingPrice;
    private double productQuantity;
    private MeasuringUnitType measuringUnitType;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private boolean productActiveState;
}
