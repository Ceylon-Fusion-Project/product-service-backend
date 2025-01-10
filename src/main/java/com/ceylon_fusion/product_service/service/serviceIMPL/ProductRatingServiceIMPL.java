package com.ceylon_fusion.product_service.service.serviceIMPL;

import com.ceylon_fusion.product_service.dto.request.ProductRatingSaveRequestDTO;
import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductRating;
import com.ceylon_fusion.product_service.repo.ProductRatingRepo;
import com.ceylon_fusion.product_service.repo.ProductRepo;
import com.ceylon_fusion.product_service.service.ProductRatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRatingServiceIMPL implements ProductRatingService {

    @Autowired
    private ProductRatingRepo productRatingRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public String saveProductRating(ProductRatingSaveRequestDTO productRatingSaveRequestDTO) {
        Integer productId = productRatingSaveRequestDTO.getProduct();

        if(productRepo.existsById(productId)){

            ProductRating newProductRating = new ProductRating(
                    productRepo.findProductByProductIDEquals(productId),
                    productRatingSaveRequestDTO.getCustomer(),
                    productRatingSaveRequestDTO.getProductRating(),
                    productRatingSaveRequestDTO.getProductReview()
            );

            productRatingRepo.save(newProductRating);

            // Get the new average rating value
            Double newRatingValue = productRatingRepo.getAverageByProductID(productId);

            // Update the product with the new average rating value
            Product product = productRepo.findProductByProductIDEquals(productId);

            product.setProductRatingValue(newRatingValue);
            productRepo.save(product);

            return "Product Rating of " + product.getProductName() + " is Updated!";
        }else{
            throw new RuntimeException("Product Not Found");
        }
    }
}
