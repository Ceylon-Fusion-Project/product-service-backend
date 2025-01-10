package com.ceylon_fusion.product_service.dto.request;

import com.ceylon_fusion.product_service.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRatingSaveRequestDTO {
    private Integer product;
    private Integer customer;
    private Integer productRating;
    private String productReview;
}
