package com.ceylon_fusion.product_service.controller;

import com.ceylon_fusion.product_service.dto.ProductRatingDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllRatingByCustomerDetailsDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllRatingByProductDetailsDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingUpdateDetailsRequestDTO;
import com.ceylon_fusion.product_service.service.ProductRatingService;
import com.ceylon_fusion.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping(path = "/get-product-ratings-by-product-id")
    public ResponseEntity<StandardResponse> getProductRatingsByProductId(
            @RequestParam(value = "productID") Integer productId,
            @RequestParam(value = "sort", required = false, defaultValue = "newest") String sort,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        try {

            // Sort Specification
            Sort sortSpec;
            switch (sort) {
                case "newest":
                    sortSpec = Sort.by("updatedDate").descending();
                    break;
                case "oldest":
                    sortSpec = Sort.by("updatedDate").ascending();
                    break;

                case "ratingAsc":
                    sortSpec = Sort.by("productRating").ascending();
                    break;
                case "ratingDesc":
                    sortSpec = Sort.by("productRating").descending();
                    break;
                default:
                    sortSpec = Sort.by("updatedDate").ascending();
            }

            // Pagination Specification
            PageRequest pageRequest = PageRequest.of(page, size, sortSpec);

            PaginatedGetAllRatingByProductDetailsDTO response = productRatingService.getProductRatingByProductId(productId,pageRequest);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Product Ratings Found", response),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(404, e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(path = "/get-product-ratings-by-user-id")
    public ResponseEntity<StandardResponse> getProductRatingsByUserId(
            @RequestParam(value = "customerID") Integer customerId,
            @RequestParam(value = "sort", required = false, defaultValue = "newest") String sort,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        try {

            // Sort Specification
            Sort sortSpec;
            switch (sort) {
                case "newest":
                    sortSpec = Sort.by("updatedDate").descending();
                    break;
                case "oldest":
                    sortSpec = Sort.by("updatedDate").ascending();
                    break;

                case "ratingAsc":
                    sortSpec = Sort.by("productRating").ascending();
                    break;
                case "ratingDesc":
                    sortSpec = Sort.by("productRating").descending();
                    break;
                default:
                    sortSpec = Sort.by("updatedDate").ascending();
            }

            // Pagination Specification
            PageRequest pageRequest = PageRequest.of(page, size, sortSpec);

            PaginatedGetAllRatingByCustomerDetailsDTO response = productRatingService.getProductRatingByCustomerId(customerId,pageRequest);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Product Ratings Found", response),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(404, e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PatchMapping(
            path = "/update-product-rating",
            params = "productRatingID"
    )
    public ResponseEntity<StandardResponse> updateProductDetails(
            @RequestBody ProductRatingUpdateDetailsRequestDTO productRatingUpdateDetailsRequestDTO,
            @RequestParam(value = "productRatingID") Integer productRatingId
    ) {
        try {
            ProductRatingDTO response = productRatingService.updateProductRating(productRatingUpdateDetailsRequestDTO, productRatingId);
           return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Product Rating Updated Successfully", response.getUpdatedDate()),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(404, e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping(
            path = "delete-product-rating-by-id",
            params = "productRatingID"
    )
    public ResponseEntity<StandardResponse> deleteProductRatingByID(
            @RequestParam(value = "productRatingID") Integer productRatingId)
    {
        try {
            String response = productRatingService.deleteProductRatingByID(productRatingId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, response, "Product Rating Deleted Successfully"),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(404, e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
