package com.ceylon_fusion.product_service.util.mappers;

import com.ceylon_fusion.product_service.dto.ProductDTO;
import com.ceylon_fusion.product_service.dto.response.ProductGetAllResponseDTO;
import com.ceylon_fusion.product_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
   // @Mapping(target = "productImageURL", ignore = true)
    List<ProductDTO> ProductEntityListToProductDTOList(Page<Product> products);

    //set target to productImageURL check if productDTO.getProductImageURLs() is not null and not empty
    @Mapping(target = "productImageURL", expression = "java(productDTOS.getProductImageURLs() != null && !productDTOS.getProductImageURLs().isEmpty() ? productDTOS.getProductImageURLs().get(0) : null)")
    ProductGetAllResponseDTO productDtoToProductGetAllResponseDto(ProductDTO productDTOS);
    List<ProductGetAllResponseDTO> productDTOListToProductGetAllResponseDTOList(List<ProductDTO> productDTOS);
}
