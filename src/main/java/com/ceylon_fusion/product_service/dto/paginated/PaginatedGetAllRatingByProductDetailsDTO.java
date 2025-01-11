package com.ceylon_fusion.product_service.dto.paginated;

import com.ceylon_fusion.product_service.dto.response.ProductGetAllResponseDTO;
import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllByProductDetailsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedGetAllRatingByProductDetailsDTO {
    List<ProductRatingGetAllByProductDetailsResponseDTO> productRatingGetAllByProductDetailsResponseDTOS;
    private long totalItems;
}
