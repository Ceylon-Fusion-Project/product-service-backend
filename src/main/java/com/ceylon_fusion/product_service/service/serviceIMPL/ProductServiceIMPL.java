package com.ceylon_fusion.product_service.service.serviceIMPL;

import com.ceylon_fusion.product_service.dto.ProductDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllProductResponseDTO;
import com.ceylon_fusion.product_service.dto.request.ProductSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.ProductUpdateDetailsRequestDTO;
import com.ceylon_fusion.product_service.dto.response.*;
import com.ceylon_fusion.product_service.entity.Certification;
import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductOrigin;
import com.ceylon_fusion.product_service.entity.ProductRating;
import com.ceylon_fusion.product_service.repo.CertificationRepo;
import com.ceylon_fusion.product_service.repo.ProductOriginRepo;
import com.ceylon_fusion.product_service.repo.ProductRatingRepo;
import com.ceylon_fusion.product_service.repo.ProductRepo;
import com.ceylon_fusion.product_service.service.ProductService;
import com.ceylon_fusion.product_service.util.mappers.CertificationMapper;
import com.ceylon_fusion.product_service.util.mappers.ProductMapper;
import com.ceylon_fusion.product_service.util.mappers.ProductOriginMapper;
import com.ceylon_fusion.product_service.util.mappers.ProductRatingMapper;
import com.ceylon_fusion.product_service.util.specifications.ProductSpecifications;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceIMPL implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CertificationRepo certificationRepo;

    @Autowired
    private ProductOriginRepo productOriginRepo;

    @Autowired
    private ProductRatingRepo productRatingRepo;

    @Autowired
    private CertificationMapper certificationMapper;

    @Autowired
    private ProductOriginMapper productOriginMapper;

    @Autowired
    private ProductRatingMapper productRatingMapper;

    @Override
    public ProductDTO saveProduct(ProductSaveRequestDTO productSaveRequestDTO) {

        if (!productRepo.existsByProductCodeEqualsIgnoreCase(productSaveRequestDTO.getProductCode())) {
            // Map ProductSaveRequestDTO to Product Entity and save
            Product newProduct = modelMapper.map(productSaveRequestDTO, Product.class);
            productRepo.save(newProduct);

            // Map Product Entity to Product DTO and return
            return modelMapper.map(newProduct, ProductDTO.class);
        } else {
            // Throw a custom exception
            throw new RuntimeException("Product with code '" + productSaveRequestDTO
                    .getProductCode() + "' is already exists.");
        }
    }

    @Override
    public PaginatedGetAllProductResponseDTO getAllProductsSorted(boolean activeStatus, Pageable pageable) {
        // Get all products with active status
        Page<Product> products = productRepo.findAllByProductActiveStateEquals(activeStatus, pageable);
        if (!products.isEmpty()) {
            // Map Product Entity List to Product DTO List
            List<ProductDTO> productDTOS = productMapper.ProductEntityListToProductDTOList(products);

            // Map Product DTO List to ProductGetAllResponseDTO List
            List<ProductGetAllResponseDTO> productGetAllResponseDTOS = productMapper
                    .productDTOListToProductGetAllResponseDTOList(productDTOS);

//            for (ProductGetAllResponseDTO productGetAllResponseDTO : productGetAllResponseDTOS) {
//                if(productRatingRepo.existsByProductID(productGetAllResponseDTO.getProductID()) > 0){
//                    Double ratingAverage = productRatingRepo.getAverageByProductID(productGetAllResponseDTO.getProductID());
//                    productGetAllResponseDTO.setRatingAverage(ratingAverage);
//                }else{
//                    productGetAllResponseDTO.setRatingAverage(null);
//                }
//            }

            // Return PaginatedGetAllProductResponseDTO
            return new PaginatedGetAllProductResponseDTO(
                    productGetAllResponseDTOS,
                    productRepo.countProductByProductActiveStateEquals(activeStatus)
            );
        } else {
            throw new RuntimeException("No Products Found");
        }
    }

    @Override
    public ProductGetAllDetailsResponseDTO getProductDetailsById(Integer productId) {
        // Get Product by Product ID
        if (productRepo.existsById(productId)) {
            // Get Product by Product ID and Map Product Entity to Product DTO
            Product product = productRepo.findProductByProductIDEquals(productId);
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

            //Get certifications by product and active state true
            // Map Certification Entity List to CertificationGetAllProductDetailsResponseDTO List
            List<Certification> certifications = certificationRepo
                    .findCertificationsByProductAndCertActiveStateEquals(product, true);
            List<CertificationGetAllProductDetailsResponseDTO> certificationsList = certificationMapper
                    .certificationEntityListToCertificationGetAllProductDetailsResponseDTOList(certifications);

            //Get Product Origin by Product and Map Product Origin Entity to ProductOriginGetAllProductDetailsResponseDTO
            ProductOrigin productOrigin = productOriginRepo.findProductOriginByProductEquals(product);
            ProductOriginGetAllProductDetailsResponseDTO productOriginResponse = productOriginMapper
                    .productOriginEntityToProductOriginGetAllProductDetailsResponseDTO(productOrigin);

            //Get Product Rating by Product
            // Map Product Rating Entity List to ProductRatingGetAllByProductDetailsResponseDTO List
            List<ProductRating> productRating = productRatingRepo.findAllByProductEquals(product);
           List<ProductRatingGetAllByProductDetailsResponseDTO> productRatingList = productRatingMapper
                    .productRatingEntityListToProductRatingGetAllProductDetailsResponseDTOList(productRating);

//            List<ProductRatingDTO> productRatingDTOList = productRatingMapper.productRatingEntityListToProductRatingDTOList(productRating);
//
//            if (!productRatingDTOList.isEmpty()) {
//                for(ProductRatingDTO productRatingDTO : productRatingDTOList) {
//                    int index = productRatingDTOList.indexOf(productRatingDTO);
//                    productRatingDTO.setProductID(productRating.get(index).getProduct().getProductID());
//                }
//            }

//            //calculate average rating value
//            Double ratingAverage = null;
//            if(productRatingRepo.existsByProductID(productId) > 0){
//                ratingAverage = productRatingRepo.getAverageByProductID(productId);
//            }

            // Return ProductGetAllDetailsResponseDTO
            return new ProductGetAllDetailsResponseDTO(
                    productDTO.getProductID(),
                    productDTO.getProductCode(),
                    productDTO.getProductName(),
                    productDTO.getProductDescription(),
                    productDTO.getSellingPrice(),
                    productDTO.getProductQuantity(),
                    productDTO.getMeasuringUnitType(),
                    productDTO.getProductImageURLs(),
                    productDTO.getProductRatingValue(),
                    certificationsList,
                    productRatingList,
                    productOriginResponse
            );
        } else {
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public ProductDTO updateProductDetails(
            ProductUpdateDetailsRequestDTO productUpdateDetailsRequestDTO,
            Integer productId) {

        if (productRepo.existsById(productId)) {
            // Get Product by Product ID
            Product existingProduct = productRepo.getReferenceById(productId);

            // Update Product name
            if (productUpdateDetailsRequestDTO.getProductName() != null) {
                existingProduct.setProductName(productUpdateDetailsRequestDTO.getProductName());
            }

            // Update Product Description
            if (productUpdateDetailsRequestDTO.getProductDescription() != null) {
                existingProduct.setProductDescription(productUpdateDetailsRequestDTO.getProductDescription());
            }

            // Update Selling Price
            if (productUpdateDetailsRequestDTO.getSellingPrice() >= 0) {
                existingProduct.setSellingPrice(productUpdateDetailsRequestDTO.getSellingPrice());
            }

            // Update Product Quantity
            if (productUpdateDetailsRequestDTO.getProductQuantity() >= 0) {
                existingProduct.setProductQuantity(productUpdateDetailsRequestDTO.getProductQuantity());
            }

            // Update Measuring Unit Type
            if (productUpdateDetailsRequestDTO.getMeasuringUnitType() != null) {
                existingProduct.setMeasuringUnitType(productUpdateDetailsRequestDTO.getMeasuringUnitType());
            }

            // Update Product Image URLs
            if (productUpdateDetailsRequestDTO.getProductImageURLs() != null) {
                existingProduct.setProductImageURLs(productUpdateDetailsRequestDTO.getProductImageURLs());
            }

            // Update Product Active State
            if (productUpdateDetailsRequestDTO.isProductActiveState() != existingProduct.isProductActiveState()) {
                existingProduct.setProductActiveState(productUpdateDetailsRequestDTO.isProductActiveState());
            }

            // Save the updated Product
            productRepo.save(existingProduct);

            return modelMapper.map(existingProduct, ProductDTO.class);
        } else {
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public String deleteProductByID(Integer productId) {
        // Get Product by Product ID
        if (productRepo.existsById(productId)) {
            String response = productRepo.getReferenceById(productId).getProductName() + " Deleted!";

            //delete product
            productRepo.deleteById(productId);
            return response;
        } else {
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public PaginatedGetAllProductResponseDTO getProductByFiltering(
            String productName,
            Double minPrice,
            Double maxPrice,
            Double averageRating,
            LocalDate startDate,
            LocalDate endDate,
            boolean activeStatus,
            Pageable pageable
    ) {
        Specification<Product> specification = Specification.
                where(ProductSpecifications.isActive(activeStatus))
                .and(ProductSpecifications.hasName(productName))
                .and(ProductSpecifications.hasPriceRange(minPrice, maxPrice))
                .and(ProductSpecifications.hasAverageRating(averageRating))
                .and(ProductSpecifications.hasCreatedDate(startDate, endDate));

        // Get all products with active status
        Page<Product> products = productRepo.findAll(specification, pageable);
        if (!products.isEmpty()) {
            // Map Product Entity List to Product DTO List
            List<ProductDTO> productDTOS = productMapper.ProductEntityListToProductDTOList(products);

            // Map Product DTO List to ProductGetAllResponseDTO List
            List<ProductGetAllResponseDTO> productGetAllResponseDTOS = productMapper
                    .productDTOListToProductGetAllResponseDTOList(productDTOS);

//            for (ProductGetAllResponseDTO productGetAllResponseDTO : productGetAllResponseDTOS) {
//                if(productRatingRepo.existsByProductID(productGetAllResponseDTO.getProductID()) > 0){
//                    Double ratingAverage = productRatingRepo.getAverageByProductID(productGetAllResponseDTO.getProductID());
//                    productGetAllResponseDTO.setRatingAverage(ratingAverage);
//                }else{
//                    productGetAllResponseDTO.setRatingAverage(null);
//                }
//            }
            return new PaginatedGetAllProductResponseDTO(
                    productGetAllResponseDTOS,
                    productRepo.count(specification)
            );
        } else {
            throw new RuntimeException("No Products Found");
        }
    }
}
