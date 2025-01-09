package com.ceylon_fusion.product_service.service;

import com.ceylon_fusion.product_service.dto.ProductDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllProductResponseDTO;
import com.ceylon_fusion.product_service.dto.request.ProductSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductUpdateDetailsRequestDTO;
import com.ceylon_fusion.product_service.dto.response.ProductGetAllDetailsResponseDTO;
import com.ceylon_fusion.product_service.dto.response.ProductGetAllResponseDTO;

import java.time.LocalDate;

public interface ProductService {
    ProductDTO saveProduct(ProductSaveRequestDTO productSaveRequestDTO);

    PaginatedGetAllProductResponseDTO getAllProducts(boolean activeStatus, Integer page, Integer size);

    ProductGetAllDetailsResponseDTO getProductDetailsById(Integer productId);

    ProductDTO updateProductDetails(ProductUpdateDetailsRequestDTO productUpdateDetailsRequestDTO, Integer productId);

    String deleteProductByID(Integer productId);

    PaginatedGetAllProductResponseDTO getProductByFiltering(String productName, Double minPrice, Double maxPrice, Double averageRating, LocalDate startDate, LocalDate endDate,boolean activeStatus, Integer page, Integer size);
}
