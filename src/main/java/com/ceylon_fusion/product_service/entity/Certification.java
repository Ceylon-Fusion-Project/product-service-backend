package com.ceylon_fusion.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Certification {
    @Id
    @Column(name = "certification_id",updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer certificationID;

    @Column(name = "certification_name", nullable = false)
    private String certificationName;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "cert_active_state", columnDefinition = "TINYINT default 1")
    private boolean certActiveState;

}
