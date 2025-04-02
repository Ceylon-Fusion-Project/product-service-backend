package com.ceylon_fusion.product_service.service.serviceIMPL;

import com.ceylon_fusion.product_service.dto.ProductRatingDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllRatingByCustomerDetailsDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllRatingByProductDetailsDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductRatingUpdateDetailsRequestDTO;
import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllByProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllByUserDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.Certification;
import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductRating;
import com.ceylon_fusion.product_service.repo.ProductRatingRepo;
import com.ceylon_fusion.product_service.repo.ProductRepo;
import com.ceylon_fusion.product_service.service.ProductRatingService;
import com.ceylon_fusion.product_service.util.mappers.ProductRatingMapper;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductRatingServiceIMPL implements ProductRatingService {

    @Autowired
    private ProductRatingRepo productRatingRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductRatingMapper productRatingMapper;

//    @Override
//    public String saveProductRating(ProductRatingSaveRequestDTO productRatingSaveRequestDTO) {
//        Integer productId = productRatingSaveRequestDTO.getProduct();
//
//        if(productRepo.existsById(productId)){
//
//            ProductRating newProductRating = new ProductRating(
//                    productRepo.findProductByProductIDEquals(productId),
//                    productRatingSaveRequestDTO.getCustomer(),
//                    productRatingSaveRequestDTO.getProductRating(),
//                    productRatingSaveRequestDTO.getProductReview()
//            );
//
//            productRatingRepo.save(newProductRating);
//
//            // Get the new average rating value
//            Double newRatingValue = productRatingRepo.getAverageByProductID(productId);
//
//            // Update the product with the new average rating value
//            Product product = productRepo.findProductByProductIDEquals(productId);
//
//            product.setProductRatingValue(newRatingValue);
//            productRepo.save(product);
//
//            return "Product Rating of " + product.getProductName() + " is Updated!";
//        }else{
//            throw new RuntimeException("Product Not Found");
//        }
//    }

    @Transactional
    @Override
    public String saveProductRating(ProductRatingSaveRequestDTO productRatingSaveRequestDTO) {
        Integer productId = productRatingSaveRequestDTO.getProduct();

        if(productRepo.existsById(productId)){
            try {
                ProductRating newProductRating = new ProductRating(
                        productRepo.findProductByProductIDEquals(productId),
                        productRatingSaveRequestDTO.getCustomer(),
                        productRatingSaveRequestDTO.getProductRating(),
                        productRatingSaveRequestDTO.getProductReview()
                );

                productRatingRepo.save(newProductRating);

                // Log the new rating
                System.out.println("New Rating Saved: " + newProductRating);

                // Update the product's average rating
                Double newRatingValue = productRatingRepo.getAverageByProductID(productId);
                Product product = productRepo.findProductByProductIDEquals(productId);

                product.setProductRatingValue(newRatingValue);
                productRepo.save(product);

                return "Product Rating of " + product.getProductName() + " is Updated!";
            } catch (Exception e) {
                e.printStackTrace(); // Log the actual error
                throw new RuntimeException("Failed to save product rating: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Product Not Found");
        }
    }


    @Override
    public PaginatedGetAllRatingByProductDetailsDTO getProductRatingByProductId(
            Integer productId,
            Pageable pageRequest)
    {

        if(productRatingRepo.existsByProduct_ProductID(productId)){
            // Get All Product Ratings by Product ID
            Page<ProductRating> productRatings = productRatingRepo
                    .findAllByProductEquals(productRepo.findProductByProductIDEquals(productId), pageRequest);

            // Map Product Rating Entity List to ProductRatingGetAllByProductDetailsResponseDTO List
            List<ProductRatingGetAllByProductDetailsResponseDTO> ratingList = productRatingMapper
                    .productRatingEntityListToProductRatingGetAllProductDetailsResponseDTOList(
                            productRatings.getContent()
                    );

            return new PaginatedGetAllRatingByProductDetailsDTO(
                    ratingList,
                    productRatingRepo.countProductRatingsByProduct_ProductID(productId)
            );
        }else{
            throw new RuntimeException("Product Ratings Not Found");
        }
    }

    @Override
    public PaginatedGetAllRatingByCustomerDetailsDTO getProductRatingByCustomerId(
            Integer customerId,
            Pageable pageable)
    {
        if(productRatingRepo.existsByCustomerEquals(customerId)){
            // Get All Product Ratings by Product ID
            Page<ProductRating> productRatings = productRatingRepo.
                    findAllByCustomerEquals(customerId, pageable);

            // Map Product Rating Entity List to ProductRatingGetAllByProductDetailsResponseDTO List
            List<ProductRatingGetAllByUserDetailsResponseDTO> ratingList = productRatingMapper
                    .productRatingEntityListToProductRatingGetAllByUserDetailsResponseDTOList(
                            productRatings.getContent()
                    );

            for (ProductRatingGetAllByUserDetailsResponseDTO list : ratingList) {
                int index = ratingList.indexOf(list);
                list.setProductID(productRatings.getContent().get(index).getProduct().getProductID());
            }

            System.out.println("ratingList = " + ratingList);

            return new PaginatedGetAllRatingByCustomerDetailsDTO(
                    ratingList,
                    productRatingRepo.countProductRatingsByCustomerEquals(customerId)
            );
        }else{
            throw new RuntimeException("Product Ratings Not Found");
        }
    }

    @Transactional
    @Override
    public ProductRatingDTO updateProductRating(
            ProductRatingUpdateDetailsRequestDTO productRatingUpdateDetailsRequestDTO,
            Integer productRatingId)
    {
        if(productRatingRepo.existsById(productRatingId)){
            // Get the existing product rating
            Optional<ProductRating> optional = productRatingRepo.findById(productRatingId);
            if (optional.isEmpty()) {
                throw new RuntimeException("Product rating not found");
            }
            ProductRating existingProductRating = optional.get();

            System.out.println("existingProductRating = " + existingProductRating);

            if(productRatingUpdateDetailsRequestDTO.getProductRating() != null){
                existingProductRating.setProductRating(productRatingUpdateDetailsRequestDTO
                        .getProductRating());
            }
            if(productRatingUpdateDetailsRequestDTO.getProductReview() != null){
                existingProductRating.setProductReview(productRatingUpdateDetailsRequestDTO
                        .getProductReview());
            }

            existingProductRating.setUpdatedDate(LocalDate.now());

            productRatingRepo.save(existingProductRating);

            // Get the new average rating value
            Double newRatingValue = productRatingRepo
                    .getAverageByProductID(existingProductRating.getProduct().getProductID());

            // Update the product with the new average rating value
            Product product = productRepo
                    .findProductByProductIDEquals(existingProductRating.getProduct().getProductID());

            product.setProductRatingValue(newRatingValue);
            productRepo.save(product);

            //return "Product Rating of " + product.getProductName() + " is Updated!";

            return productRatingMapper.productRatingEntityToProductRatingDTO(existingProductRating);
        }else{
            throw new RuntimeException("Product Rating Not Found");
        }
    }

    @Override
    public String deleteProductRatingByID(Integer productRatingId) {
        // check if product Rating exists
        if (productRatingRepo.existsById(productRatingId)) {

            // Get the relevant product ID
            Integer productId = productRatingRepo.getReferenceById(productRatingId).getProduct().getProductID();

            //delete product rating
            productRatingRepo.deleteById(productRatingId);

            // Get the new average rating value for the product
            Double newRatingValue = productRatingRepo.getAverageByProductID(productId);

            // Update the product with the new average rating value
            Product product = productRepo.findProductByProductIDEquals(productId);

            product.setProductRatingValue(newRatingValue);
            productRepo.save(product);

            return "Rating Deleted Successfully and Product Rating of " + product.getProductName() + " is Updated!";
        } else {
            throw new RuntimeException("Product Rating Not Found");
        }
    }
}
