package com.ceylon_fusion.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "certification")
public class Certification {
    @Id
    @Column(name = "certification_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer certificationID;

    // ManyToOne -> Product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "certification_name", nullable = false)
    private String certificationName;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "product_active_state", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean certActiveState;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", updatable = false)
    private LocalDate updatedDate;

    @Column(name = "cert_url")
    private String certURL;

}
