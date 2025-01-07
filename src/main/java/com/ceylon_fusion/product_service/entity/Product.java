package com.ceylon_fusion.product_service.entity;

import com.ceylon_fusion.product_service.entity.enums.MeasuringUnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @Column(name = "product_id",updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;

    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @Column(name = "product_quantity", nullable = false)
    private double productQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_unit_type", nullable = false)
    private MeasuringUnitType measuringUnitType;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @CreationTimestamp
    @Column(name = "updated_date", updatable = false)
    private LocalDate updatedDate;

    @Column(name = "product_active_state", columnDefinition = "TINYINT default 1")
    private boolean productActiveState;

}
