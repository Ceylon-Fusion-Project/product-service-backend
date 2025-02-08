package com.ceylon_fusion.product_service.controller;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllCertificationsDTO;
import com.ceylon_fusion.product_service.dto.request.CertificateSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.CertificationUpdateRequestDTO;
import com.ceylon_fusion.product_service.service.CertificationService;
import com.ceylon_fusion.product_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/certificate")
@CrossOrigin
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @PostMapping(path = "/save-certificate")
    public ResponseEntity<StandardResponse> SaveCertificate(
            @RequestBody CertificateSaveRequestDTO certificateSaveRequestDTO) {
        try {
            CertificationDTO response = certificationService.saveCertificate(certificateSaveRequestDTO);

            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Certificate Saved Successfully", response.getCertificationName()),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(409, e.getMessage(), null),
                    HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping(path = "/get-certificate-by-product-id")
    public ResponseEntity<StandardResponse> getCertificateByProductId(
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
                    sortSpec = Sort.by("issuedDate").descending();
                    break;
                case "oldest":
                    sortSpec = Sort.by("issuedDate").ascending();
                    break;

                case "nameAsc":
                    sortSpec = Sort.by("certificationName").ascending();
                    break;
                case "nameDesc":
                    sortSpec = Sort.by("certificationName").descending();
                    break;
                default:
                    sortSpec = Sort.by("issuedDate").ascending();
            }

            // Pagination Specification
            PageRequest pageRequest = PageRequest.of(page, size, sortSpec);

            PaginatedGetAllCertificationsDTO response = certificationService.getCertificationsByProductId(productId, pageRequest);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Certificate Found", response),
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
            path = "/update-certificate",
            params = "certificationID"
    )
    public ResponseEntity<StandardResponse> updateCertificateDetails(
            @RequestBody CertificationUpdateRequestDTO certificationUpdateRequestDTO,
            @RequestParam(value = "certificationID") Integer certificationId
    ) {
        try {
            CertificationDTO response = certificationService.updateCertificate(certificationUpdateRequestDTO, certificationId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Certificate Updated Successfully", response.getUpdatedDate()),
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
            path = "delete-certificate-by-id",
            params = "certificationID"
    )
    public ResponseEntity<StandardResponse> deleteProductRatingByID(
            @RequestParam(value = "certificationID") Integer certificationId)
    {
        try {
            String response = certificationService.deleteCertificationByID(certificationId);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, response, "Certification Deleted Successfully"),
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
