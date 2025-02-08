package com.ceylon_fusion.product_service.util.specifications;

import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductRating;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ProductSpecifications {
    //filter by product name
    public static Specification<Product> hasName(String name) {
        //lambda expression{root->entity, query->criteria, criteriaBuilder->build predicates (conditions)}
        return (root, query, criteriaBuilder) -> {
            if(name == null || name.isEmpty()) {
                //no filter
                //a predicate that always evaluates to true
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("productName")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    //filter by minimum and maximum price
    public static Specification<Product> hasPriceRange(Double minPrice, Double maxPrice) {
        //lambda expression
        return (root, query, criteriaBuilder) -> {
            if(minPrice == null && maxPrice == null) {
                //no filter
                return criteriaBuilder.conjunction();
            }
            //create initial predicate(clause in a SQL WHERE statement)
            Predicate predicate = criteriaBuilder.conjunction();
            if(minPrice != null) {
                //combine predicates using and
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice)
                );

            }
            if(maxPrice != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice)
                );
            }
            //return the combined predicates(all conditions)
            return predicate;
        };
    }

    //filter by created date
    public static Specification<Product> hasCreatedDate(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
             if(startDate == null && endDate == null) {
                return criteriaBuilder.conjunction();
        };
            Predicate predicate = criteriaBuilder.conjunction();
            if(startDate != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), startDate)
                );
            }
            if(endDate != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), endDate)
                );
            }
            return predicate;
        };
    }

    //filter by average rating
    public static Specification<Product> hasAverageRating(Double minAvgRating) {
        return (root, query, criteriaBuilder) -> {
            if(minAvgRating == null) {
                return criteriaBuilder.conjunction();
            }

            //create a subquery
            assert query != null;
            Subquery<Double> subquery = query.subquery(Double.class);

            //FROM ProductRating pr
            Root<ProductRating> productRatingRoot = subquery.from(ProductRating.class);

            // Select the average rating for the current product
            subquery.select(criteriaBuilder.avg(productRatingRoot.get("productRating")))
                    .where(criteriaBuilder.equal(
                            productRatingRoot.get("product").get("productID"),
                            root.get("productID")
                    ));

            return criteriaBuilder.greaterThanOrEqualTo(subquery, minAvgRating);
        };
    }

    public static Specification<Product> isActive(boolean activeStatus) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("productActiveState"), activeStatus);
    }
}
