package com.ceylon_fusion.product_service.entity;

import com.ceylon_fusion.product_service.entity.enums.MeasuringUnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;

    @Column(name = "product_code", nullable = false, unique = true, updatable = false)
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

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_urls")
    private List<String> productImageURLs = new ArrayList<>();


    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", updatable = false)
    private LocalDate updatedDate;

    @Column(name = "product_active_state",columnDefinition = "BOOLEAN DEFAULT TRUE", insertable = false,updatable = true)
    private boolean productActiveState;

    // OneToMany -> Certification
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certification> certifications;

    // OneToOne -> ProductOrigin
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductOrigin productOrigin;

    // OneToOne -> ProductRating
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductRating productRating;

}
