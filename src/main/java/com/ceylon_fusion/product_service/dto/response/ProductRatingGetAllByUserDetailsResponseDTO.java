package com.ceylon_fusion.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRatingGetAllByUserDetailsResponseDTO {
    private Integer productRatingID;
    private Integer productID;
    private Integer productRating;
    private String productReview;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
