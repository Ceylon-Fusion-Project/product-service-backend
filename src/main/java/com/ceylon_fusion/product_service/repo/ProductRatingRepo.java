package com.ceylon_fusion.product_service.repo;

import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRatingRepo extends JpaRepository<ProductRating, Integer>, JpaSpecificationExecutor<ProductRating> {
    List<ProductRating> findAllByProductEquals(Product product);

    @Query(value = "select avg(rating_value) from product_rating where product_id = ?1", nativeQuery = true)
    Double getAverageByProductID(Integer productID);

    @Query(value = "select count(*) from product_rating where product_id = ?1", nativeQuery = true)
    Integer existsByProductID(Integer productID);

    Page<ProductRating> findAllByProductEquals(Product productByProductIDEquals, Pageable pageRequest);

    long getCountByProductEquals(Product product);

    long countProductRatingsByProduct_ProductID(Integer productProductID);

    Page<ProductRating> findAllByCustomerEquals(Integer customerId, Pageable pageable);

    long countProductRatingsByCustomerEquals(Integer customerId);

    boolean existsByCustomerEquals(Integer customerId);

    boolean existsByProduct_ProductID(Integer productId);
}
