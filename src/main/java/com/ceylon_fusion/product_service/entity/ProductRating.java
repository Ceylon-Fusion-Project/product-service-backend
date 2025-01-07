package com.ceylon_fusion.product_service.entity;

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
public class ProductRating {
    @Id
    @Column(name = "product_rating_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productRatingID;

    @Column(name = "product_rating", nullable = false)
    private Integer productRating;

    @Column(name = "product_review")
    private String productReview;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;
}
