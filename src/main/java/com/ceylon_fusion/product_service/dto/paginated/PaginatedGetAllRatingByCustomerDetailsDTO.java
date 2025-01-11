package com.ceylon_fusion.product_service.dto.paginated;

import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllByUserDetailsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedGetAllRatingByCustomerDetailsDTO {
    List<ProductRatingGetAllByUserDetailsResponseDTO> productRatingGetAllByUserDetailsResponseDTOS;
    private long totalItems;
}
