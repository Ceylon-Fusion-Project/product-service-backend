package com.ceylon_fusion.product_service.repo;

import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProductOriginRepo extends JpaRepository<ProductOrigin, Integer> {

    ProductOrigin findProductOriginByProductEquals(Product product);
}
