package com.ceylon_fusion.product_service.service;

import com.ceylon_fusion.product_service.dto.ProductRatingDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllRatingByCustomerDetailsDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllRatingByProductDetailsDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingUpdateDetailsRequestDTO;
import org.springframework.data.domain.Pageable;

public interface ProductRatingService {
    String saveProductRating(ProductRatingSaveRequestDTO productRatingSaveRequestDTO);

    PaginatedGetAllRatingByProductDetailsDTO getProductRatingByProductId(Integer productId, Pageable pageable);

    PaginatedGetAllRatingByCustomerDetailsDTO getProductRatingByCustomerId(Integer customerId, Pageable pageable);

    ProductRatingDTO updateProductRating(ProductRatingUpdateDetailsRequestDTO productRatingUpdateDetailsRequestDTO, Integer productRatingId);

    String deleteProductRatingByID(Integer productRatingId);
}
