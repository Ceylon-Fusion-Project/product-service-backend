package com.ceylon_fusion.product_service.controller;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.ProductOriginDTO;
import com.ceylon_fusion.product_service.dto.request.CertificationUpdateRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductOriginSaveAndUpdateRequestDTO;
import com.ceylon_fusion.product_service.service.ProductOriginService;
import com.ceylon_fusion.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/product-origin")
@CrossOrigin
public class ProductOriginController {

    @Autowired
    private ProductOriginService productOriginService;

    @PostMapping(path = "/save-origin")
    public ResponseEntity<StandardResponse> SaveProductOrigin(
            @RequestBody ProductOriginSaveAndUpdateRequestDTO productOriginSaveRequestDTO) {
        try {
            ProductOriginDTO response = productOriginService.saveProductOrigin(productOriginSaveRequestDTO);

            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Origin Saved Successfully", response.getStateLocation()),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(409, e.getMessage(), null),
                    HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping(path = "/get-origin-by-origin-id")
    public ResponseEntity<StandardResponse> getProductOriginByoriginId(
            @RequestParam(value = "originID") Integer originId
    ) {
        try {
            ProductOriginDTO response = productOriginService.getProductOriginByOriginId(originId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Product Origin Found", response),
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
            path = "/update-origin",
            params = "originID"
    )
    public ResponseEntity<StandardResponse> updateProductOriginDetails(
            @RequestBody ProductOriginSaveAndUpdateRequestDTO updateRequestDTO,
            @RequestParam(value = "originID") Integer originId
    ) {
        try {
            CertificationDTO response = productOriginService.updateOrigin(updateRequestDTO, originId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Origin Updated Successfully", response.getUpdatedDate()),
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
            path = "delete-origin-by-id",
            params = "originID"
    )
    public ResponseEntity<StandardResponse> deleteProductRatingByID(
            @RequestParam(value = "originID") Integer originId) {
        try {
            String response = productOriginService.deleteOriginByID(originId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, response, "Origin Details Deleted Successfully"),
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
