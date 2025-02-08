package com.ceylon_fusion.product_service.controller;

import com.ceylon_fusion.product_service.dto.ProductDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllProductResponseDTO;
import com.ceylon_fusion.product_service.dto.request.ProductSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductUpdateDetailsRequestDTO;
import com.ceylon_fusion.product_service.dto.response.ProductGetAllDetailsResponseDTO;
import com.ceylon_fusion.product_service.service.ProductService;
import com.ceylon_fusion.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "api/v1/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/save-product")
    public ResponseEntity<StandardResponse> saveProduct(@RequestBody ProductSaveRequestDTO productSaveRequestDTO) {
        try {
            ProductDTO response = productService.saveProduct(productSaveRequestDTO);

            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Product Saved Successfully", response.getProductName()),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(409, e.getMessage(), null),
                    HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping(
            path = "/get-all-products",
            params = {"page", "size", "status"}
    )
    public ResponseEntity<StandardResponse> getAllProductsWithSort(
            @RequestParam(value = "status",defaultValue = "true",required = false) boolean activeStatus,
            @RequestParam(value = "sort",required = false,defaultValue = "nameAsc") String sort,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10",required = false) Integer size
    ) {
        try {
            // Sort Specification
            Sort sortSpec;
            switch (sort) {
                case "nameAsc":
                    sortSpec = Sort.by("productName").ascending();
                    break;
                case "nameDesc":
                    sortSpec = Sort.by("productName").descending();
                    break;
                case "priceAsc":
                    sortSpec = Sort.by("sellingPrice").ascending();
                    break;
                case "priceDesc":
                    sortSpec = Sort.by("sellingPrice").descending();
                    break;
                case "ratingAsc":
                    sortSpec = Sort.by("productRatingValue").ascending();
                    break;
                case "ratingDesc":
                    sortSpec = Sort.by("productRatingValue").descending();
                    break;
                case "oldest":
                    sortSpec = Sort.by("createdDate").ascending();
                    break;
                case "newest":
                    sortSpec = Sort.by("createdDate").descending();
                    break;
                case "quantityAsc":
                    sortSpec = Sort.by("productQuantity").ascending();
                    break;
                case "quantityDesc":
                    sortSpec = Sort.by("productQuantity").descending();
                    break;
                default:
                    sortSpec = Sort.by("productName").ascending();
            }

            // Page Request Specification
            PageRequest pageRequest = PageRequest.of(page, size, sortSpec);

            PaginatedGetAllProductResponseDTO response = productService.getAllProductsSorted(activeStatus, pageRequest);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "All Products", response),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(404, e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(
            path = "/get-product-details-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getProductById(@RequestParam(value = "id") Integer productId) {
        try {
            ProductGetAllDetailsResponseDTO response = productService.getProductDetailsById(productId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Product Found", response),
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
            path = "/update-product-details",
            params = "id"
    )
    public ResponseEntity<StandardResponse> updateProductDetails(
            @RequestBody ProductUpdateDetailsRequestDTO productUpdateDetailsRequestDTO,
            @RequestParam(value = "id") Integer productId
    ) {
        try {
            ProductDTO response = productService.updateProductDetails(productUpdateDetailsRequestDTO, productId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Product Updated Successfully", response.getProductName()),
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
            path = "delete-product-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> deleteProductByID(@RequestParam(value = "id") Integer productId) {
        try {
            String response = productService.deleteProductByID(productId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, response, "Product Deleted Successfully"),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(404, e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(path = "/get-product-by-filtering")
    public ResponseEntity<StandardResponse> getProductByFiltering(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double averageRating,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false,defaultValue = "true" ) boolean activeStatus,
            @RequestParam(value = "sort",required = false,defaultValue = "nameAsc") String sort,
            @RequestParam(required = false,defaultValue = "0") Integer page,
            @RequestParam(required = false,defaultValue = "10") Integer size
    ) {
        try {
            // Sort Specification
            Sort sortSpec;
            switch (sort) {
                case "nameAsc":
                    sortSpec = Sort.by("productName").ascending();
                    break;
                case "nameDesc":
                    sortSpec = Sort.by("productName").descending();
                    break;
                case "priceAsc":
                    sortSpec = Sort.by("sellingPrice").ascending();
                    break;
                case "priceDesc":
                    sortSpec = Sort.by("sellingPrice").descending();
                    break;
                case "ratingAsc":
                    sortSpec = Sort.by("productRatingValue").ascending();
                    break;
                case "ratingDesc":
                    sortSpec = Sort.by("productRatingValue").descending();
                    break;
                case "oldest":
                    sortSpec = Sort.by("createdDate").ascending();
                    break;
                case "newest":
                    sortSpec = Sort.by("createdDate").descending();
                    break;
                case "quantityAsc":
                    sortSpec = Sort.by("productQuantity").ascending();
                    break;
                case "quantityDesc":
                    sortSpec = Sort.by("productQuantity").descending();
                    break;
                default:
                    sortSpec = Sort.by("productName").ascending();
            }

            // Page Request Specification
            PageRequest pageRequest = PageRequest.of(page, size, sortSpec);
            PaginatedGetAllProductResponseDTO response = productService.getProductByFiltering(productName, minPrice, maxPrice, averageRating, startDate, endDate,activeStatus, pageRequest);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Product Found", response),
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
