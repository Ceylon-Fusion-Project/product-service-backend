package com.ceylon_fusion.product_service.repo;

import com.ceylon_fusion.product_service.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProductRatingRepo extends JpaRepository<ProductRating, Long> {
}
