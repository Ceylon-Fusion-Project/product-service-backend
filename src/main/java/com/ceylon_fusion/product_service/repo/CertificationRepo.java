package com.ceylon_fusion.product_service.repo;

import com.ceylon_fusion.product_service.entity.Certification;
import com.ceylon_fusion.product_service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CertificationRepo extends JpaRepository<Certification, Integer> {
    List<Certification> findCertificationsByProductAndCertActiveStateEquals(Product product, boolean b);

    boolean existsByProduct_ProductID(Integer productId);

    Page<Certification> findAllByProductEquals(Product product, Pageable pageRequest);

    long countProductRatingsByProduct_ProductID(Integer productId);
}
