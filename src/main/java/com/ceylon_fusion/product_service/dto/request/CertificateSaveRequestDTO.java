package com.ceylon_fusion.product_service.dto.request;

import com.ceylon_fusion.product_service.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
