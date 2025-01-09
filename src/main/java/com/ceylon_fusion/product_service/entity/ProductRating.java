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
@Table(name = "product_rating")
public class ProductRating {
    @Id
    @Column(name = "product_rating_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productRatingID;

    // OneToOne -> Product
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "customer_id", nullable = false)
    private Integer customerID;

    @Column(name = "rating_value", nullable = false)
    private Integer productRating;

    @Column(name = "product_review")
    private String productReview;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", updatable = false)
    private LocalDate updatedDate;
}
