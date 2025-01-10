package com.ceylon_fusion.product_service.controller;

import com.ceylon_fusion.product_service.dto.ProductDTO;
import com.ceylon_fusion.product_service.dto.ProductRatingDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductSaveRequestDTO;
import com.ceylon_fusion.product_service.service.ProductRatingService;
import com.ceylon_fusion.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/product-rating")
@CrossOrigin
public class ProductRatingController {

    @Autowired
    private ProductRatingService productRatingService;

    @PostMapping(path = "/save-product-rating")
    public ResponseEntity<StandardResponse> saveProduct(@RequestBody ProductRatingSaveRequestDTO productRatingSaveRequestDTO) {
        try {
            String response = productRatingService.saveProductRating(productRatingSaveRequestDTO);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Add Rating Successfully", response),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(409, e.getMessage(), null),
                    HttpStatus.CONFLICT
            );
        }
    }
}
