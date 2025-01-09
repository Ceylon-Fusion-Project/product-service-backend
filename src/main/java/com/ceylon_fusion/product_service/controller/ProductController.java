package com.ceylon_fusion.product_service.controller;

import com.ceylon_fusion.product_service.dto.ProductDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllProductResponseDTO;
import com.ceylon_fusion.product_service.dto.request.ProductSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductUpdateDetailsRequestDTO;
import com.ceylon_fusion.product_service.dto.response.ProductGetAllDetailsResponseDTO;
import com.ceylon_fusion.product_service.service.ProductService;
import com.ceylon_fusion.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<StandardResponse> getAllProducts(
            @RequestParam(value = "status") boolean activeStatus,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        try {
            PaginatedGetAllProductResponseDTO response = productService.getAllProducts(activeStatus, page, size);
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

    @GetMapping(
            path = "/get-product-by-filtering"
    )
    public ResponseEntity<StandardResponse> getProductByFiltering(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double averageRating,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false,defaultValue = "true" ) boolean activeStatus,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        try {
            PaginatedGetAllProductResponseDTO response = productService.getProductByFiltering(productName, minPrice, maxPrice, averageRating, startDate, endDate,activeStatus, page, size);
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
