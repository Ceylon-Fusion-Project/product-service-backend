package com.ceylon_fusion.product_service.service;

import com.ceylon_fusion.product_service.dto.ProductDTO;
import com.ceylon_fusion.product_service.dto.ProductRatingDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingSaveRequestDTO;

public interface ProductRatingService {
    String saveProductRating(ProductRatingSaveRequestDTO productRatingSaveRequestDTO);
}
