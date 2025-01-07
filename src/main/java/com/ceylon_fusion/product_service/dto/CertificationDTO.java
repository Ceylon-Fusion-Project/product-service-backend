package com.ceylon_fusion.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CertificationDTO {
    private Integer certificationID;
    private String certificationName;
    private String issuer;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private boolean certActiveState;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
