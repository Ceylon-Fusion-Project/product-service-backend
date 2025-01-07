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
public class ProductOrigin {
    @Id
    @Column(name = "origin_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer originID;

    @Column(name = "state_location", nullable = false)
    private String stateLocation;

    @Column(name = "state_map_link", nullable = false)
    private String stateMapLink;

    @Column(name = "part_of_plant", nullable = false)
    private String partOfPlant;

    @Column(name = "origin_description")
    private String originDescription;

    @Column(name = "factory_name")
    private String factoryName;

    @Column(name = "factory_address")
    private String factoryAddress;

    @Column(name = "factory_map_link")
    private String factoryMapLink;

    @Column(name = "demo_video_link")
    private String demoVideoLink;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @CreationTimestamp
    @Column(name = "updated_date", updatable = false)
    private LocalDate updatedDate;

}

