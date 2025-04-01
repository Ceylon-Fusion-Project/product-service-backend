package com.ceylon_fusion.product_service.service;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.ProductOriginDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllOrigins;
import com.ceylon_fusion.product_service.dto.request.ProductOriginSaveAndUpdateRequestDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ProductOriginService {
    ProductOriginDTO saveProductOrigin(ProductOriginSaveAndUpdateRequestDTO productOriginSaveRequestDTO);

    ProductOriginDTO updateOrigin(ProductOriginSaveAndUpdateRequestDTO updateRequestDTO, Integer originId);

    String deleteOriginByID(Integer originId);

    ProductOriginDTO getProductOriginByOriginId(Integer originId);

    PaginatedGetAllOrigins getAllOrigins(Pageable pageable);
}
