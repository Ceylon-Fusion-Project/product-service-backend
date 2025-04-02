package com.ceylon_fusion.product_service.util.mappers;

import com.ceylon_fusion.product_service.dto.ProductRatingDTO;
import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllByProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllByUserDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.ProductRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRatingMapper {
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "productID", target = "productID")
    List<ProductRatingDTO> productRatingEntityListToProductRatingDTOList(List<ProductRating> productRating);

    // Map Product Rating Entity List to ProductRatingGetAllByProductDetailsResponseDTO List
    List<ProductRatingGetAllByProductDetailsResponseDTO>
    productRatingEntityListToProductRatingGetAllProductDetailsResponseDTOList(List<ProductRating> productRating);

    // Map Product Rating Entity List to ProductRatingGetAllByUserDetailsResponseDTO List
    List<ProductRatingGetAllByUserDetailsResponseDTO>
    productRatingEntityListToProductRatingGetAllByUserDetailsResponseDTOList(List<ProductRating> content);

    @Mapping(source = "product.productID", target = "productID")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "productRating", target = "productRating")
    @Mapping(source = "productReview", target = "productReview")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "updatedDate", target = "updatedDate")
    ProductRatingDTO productRatingEntityToProductRatingDTO(ProductRating existingProductRating);
}
