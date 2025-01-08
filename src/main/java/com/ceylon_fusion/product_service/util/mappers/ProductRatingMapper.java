package com.ceylon_fusion.product_service.util.mappers;

import com.ceylon_fusion.product_service.dto.ProductRatingDTO;
import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.ProductRating;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRatingMapper {
    List<ProductRatingDTO> productRatingEntityListToProductRatingDTOList(List<ProductRating> productRating);

    List<ProductRatingGetAllProductDetailsResponseDTO> productRatingEntityListToProductRatingGetAllProductDetailsResponseDTOList(List<ProductRating> productRating);
}
