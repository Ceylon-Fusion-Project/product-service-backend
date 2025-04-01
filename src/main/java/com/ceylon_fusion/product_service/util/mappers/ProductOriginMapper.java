package com.ceylon_fusion.product_service.util.mappers;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.ProductOriginDTO;
import com.ceylon_fusion.product_service.dto.request.ProductOriginSaveAndUpdateRequestDTO;
import com.ceylon_fusion.product_service.dto.response.ProductOriginGetAllProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.ProductOrigin;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOriginMapper {
    ProductOriginDTO productOriginEntityToProductOriginDTO(ProductOrigin productOrigin);

    ProductOriginGetAllProductDetailsResponseDTO productOriginEntityToProductOriginGetAllProductDetailsResponseDTO(ProductOrigin productOrigin);

    CertificationDTO productOriginEntityToCertificationDTO(ProductOrigin existingproductOrigin);

    ProductOrigin productOriginSaveAndUpdateRequestDTOToProductOriginEntity(ProductOriginSaveAndUpdateRequestDTO productOriginSaveRequestDTO);

    List<ProductOriginGetAllProductDetailsResponseDTO> productOriginEntityListToProductOriginGetAllProductDetailsResponseDTOList(Page<ProductOrigin> productOrigins);
}
