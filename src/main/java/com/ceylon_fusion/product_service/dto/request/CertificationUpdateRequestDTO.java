package com.ceylon_fusion.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CertificationUpdateRequestDTO {
    private Integer productID;
    private String certificationName;
    private String issuer;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private boolean certActiveState;
    private String certURL;

    public boolean getCertActiveState() {
        return certActiveState;
    }
}
