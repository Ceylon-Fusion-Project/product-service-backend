package com.ceylon_fusion.product_service.util.mappers;

import com.ceylon_fusion.product_service.dto.ProductOriginDTO;
import com.ceylon_fusion.product_service.dto.response.ProductOriginGetAllProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.ProductOrigin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOriginMapper {
    ProductOriginDTO productOriginEntityToProductOriginDTO(ProductOrigin productOrigin);

    ProductOriginGetAllProductDetailsResponseDTO productOriginEntityToProductOriginGetAllProductDetailsResponseDTO(ProductOrigin productOrigin);
}
