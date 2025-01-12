package com.ceylon_fusion.product_service.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CertificateSaveRequestDTO {
    private Integer productID;
    private String certificationName;
    private String issuer;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private String certURL;
}
