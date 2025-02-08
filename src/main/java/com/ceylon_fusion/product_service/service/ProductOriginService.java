package com.ceylon_fusion.product_service.service;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.ProductOriginDTO;
import com.ceylon_fusion.product_service.dto.request.ProductOriginSaveAndUpdateRequestDTO;

public interface ProductOriginService {
    ProductOriginDTO saveProductOrigin(ProductOriginSaveAndUpdateRequestDTO productOriginSaveRequestDTO);

    CertificationDTO updateOrigin(ProductOriginSaveAndUpdateRequestDTO updateRequestDTO, Integer originId);

    String deleteOriginByID(Integer originId);

    ProductOriginDTO getProductOriginByOriginId(Integer originId);
}
