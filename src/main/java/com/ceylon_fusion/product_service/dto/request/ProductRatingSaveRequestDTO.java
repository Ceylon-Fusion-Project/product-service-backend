package com.ceylon_fusion.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRatingSaveRequestDTO {
    private Integer product;
    private Integer customer;
    private Integer productRating;
    private String productReview;
}
