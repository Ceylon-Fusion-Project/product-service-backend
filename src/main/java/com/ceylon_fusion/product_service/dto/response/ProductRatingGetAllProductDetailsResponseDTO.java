package com.ceylon_fusion.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRatingGetAllProductDetailsResponseDTO {
    private Integer customerID;
    private Integer productRating;
    private String productReview;
    private LocalDate createdDate;
}
