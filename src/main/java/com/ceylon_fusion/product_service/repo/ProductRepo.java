package com.ceylon_fusion.product_service.repo;

import com.ceylon_fusion.product_service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRepo extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    boolean existsByProductCodeEqualsIgnoreCase(String productCode);

    Page<Product> findAllByProductActiveStateEquals(boolean activeStatus, Pageable pageable);

    long countProductByProductActiveStateEquals(boolean activeStatus);

    Product findProductByProductIDEquals(Integer productId);

    //Page<Product> findAll(Specification<Product> specification, Pageable pageable);

}
