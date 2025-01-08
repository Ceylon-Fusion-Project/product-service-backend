package com.ceylon_fusion.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CertificationGetAllProductDetailsResponseDTO {
    private String certificationName;
    private String issuer;
    private String certURL;
}
