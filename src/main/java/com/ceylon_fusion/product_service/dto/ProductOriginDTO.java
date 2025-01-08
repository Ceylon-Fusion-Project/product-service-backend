package com.ceylon_fusion.product_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOriginDTO {
    private Integer originID;
    private Integer productID;
    private String stateLocation;
    private String stateMapLink;
    private String partOfPlant;
    private String originDescription;
    private String factoryName;
    private String factoryAddress;
    private String factoryMapLink;
    private String demoVideoLink;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
