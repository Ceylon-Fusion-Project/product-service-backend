package com.ceylon_fusion.product_service.repo;

import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRatingRepo extends JpaRepository<ProductRating, Long> {
    List<ProductRating> findAllByProductEquals(Product product);

    @Query(value = "select avg(rating_value) from product_rating where product_id = ?1", nativeQuery = true)
    Double getAverageByProductID(Integer productID);

    @Query(value = "select count(*) from product_rating where product_id = ?1", nativeQuery = true)
    Integer existsByProductID(Integer productID);

}
