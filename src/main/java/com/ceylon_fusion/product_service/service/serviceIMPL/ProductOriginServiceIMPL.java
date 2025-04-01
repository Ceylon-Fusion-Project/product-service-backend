package com.ceylon_fusion.product_service.service.serviceIMPL;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.ProductOriginDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllOrigins;
import com.ceylon_fusion.product_service.dto.request.ProductOriginSaveAndUpdateRequestDTO;
import com.ceylon_fusion.product_service.dto.response.ProductOriginGetAllProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.ProductOrigin;
import com.ceylon_fusion.product_service.repo.ProductOriginRepo;
import com.ceylon_fusion.product_service.repo.ProductRepo;
import com.ceylon_fusion.product_service.service.ProductOriginService;
import com.ceylon_fusion.product_service.util.mappers.ProductOriginMapper;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOriginServiceIMPL implements ProductOriginService {

    @Autowired
    private ProductOriginRepo productOriginRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductOriginMapper productOriginMapper;

    @Override
    public ProductOriginDTO saveProductOrigin(ProductOriginSaveAndUpdateRequestDTO productOriginSaveRequestDTO) {

        if (!productOriginRepo.existsByOriginCodeEqualsIgnoreCase(productOriginSaveRequestDTO.getOriginCode())) {

            ProductOrigin newProductOrigin = productOriginMapper.productOriginSaveAndUpdateRequestDTOToProductOriginEntity(productOriginSaveRequestDTO);

            productOriginRepo.save(newProductOrigin);

            return productOriginMapper.productOriginEntityToProductOriginDTO(newProductOrigin);
        } else {
            throw new RuntimeException("Product Origin Already Exists!");
        }
    }

//    @Override
//    public ProductOriginDTO getProductOriginByProductId(Integer productId) {
//
//        if(productRepo.existsById(productId)) {
//            ProductOrigin productOrigin = productOriginRepo.findProductOriginByProduct(productRepo.findProductByProductIDEquals(productId));
//            if(productOrigin != null){
//                ProductOriginDTO productOriginDTO = productOriginMapper.productOriginEntityToProductOriginDTO(productOrigin);
//                productOriginDTO.setProductID(productId);
//
//                return productOriginDTO;
//            }else{
//                throw new RuntimeException("Product Origin Not Found with that Product ID");
//            }
//        }else{
//            throw new RuntimeException("Product Not Found with that Product ID");
//        }
//    }

    @Transactional
    @Override
    public ProductOriginDTO updateOrigin(ProductOriginSaveAndUpdateRequestDTO updateRequestDTO, Integer originId) {
        if (productOriginRepo.existsById(originId)) {

            //ProductOrigin existingproductOrigin = productOriginRepo.findById(originId);
            Optional<ProductOrigin> optional = productOriginRepo.findById(originId);
            if (optional.isEmpty()) {
                throw new NotFoundException("Origin not found");
            }
            ProductOrigin existingproductOrigin = optional.get();
            System.out.println(existingproductOrigin);

            if (updateRequestDTO.getStateLocation() != null) {
                existingproductOrigin.setStateLocation(updateRequestDTO.getStateLocation());
            }
            if (updateRequestDTO.getStateMapLink() != null) {
                existingproductOrigin.setStateMapLink(updateRequestDTO.getStateMapLink());
            }
            if (updateRequestDTO.getPartOfPlant() != null) {
                existingproductOrigin.setPartOfPlant(updateRequestDTO.getPartOfPlant());
            }
            if (updateRequestDTO.getOriginDescription() != null) {
                existingproductOrigin.setOriginDescription(updateRequestDTO.getOriginDescription());
            }
            if (updateRequestDTO.getFactoryName() != null) {
                existingproductOrigin.setFactoryName(updateRequestDTO.getFactoryName());
            }
            if (updateRequestDTO.getFactoryAddress() != null) {
                existingproductOrigin.setFactoryAddress(updateRequestDTO.getFactoryAddress());
            }
            if (updateRequestDTO.getFactoryMapLink() != null) {
                existingproductOrigin.setFactoryMapLink(updateRequestDTO.getFactoryMapLink());
            }
            if (updateRequestDTO.getDemoVideoLink() != null) {
                existingproductOrigin.setDemoVideoLink(updateRequestDTO.getDemoVideoLink());
            }

            productOriginRepo.save(existingproductOrigin);

            return productOriginMapper.productOriginEntityToProductOriginDTO(existingproductOrigin);
        } else {
            throw new RuntimeException("Product Origin Details Not Found!");
        }
    }

    @Override
    public String deleteOriginByID(Integer originId) {
        // check if Origin exists
        if (productOriginRepo.existsById(originId)) {

            //delete Origin by certification id
            productOriginRepo.deleteById(originId);
            return "Product Origin Details Deleted Successfully!";

        } else {
            throw new RuntimeException("Product Origin Details Not Found");
        }
    }

    @Override
    public ProductOriginDTO getProductOriginByOriginId(Integer originId) {
        if (productOriginRepo.existsById(originId)) {
            ProductOrigin productOrigin = productOriginRepo.findById(originId)
                    .orElseThrow(() -> new RuntimeException("Product Origin Not Found!"));
            return productOriginMapper.productOriginEntityToProductOriginDTO(productOrigin);
        } else {
            throw new RuntimeException("Product Origin Not Found!");
        }
    }

    @Override
    public PaginatedGetAllOrigins getAllOrigins(Pageable pageable) {
        Page<ProductOrigin> productOrigins = productOriginRepo.findAll(pageable);

        if(!productOrigins.isEmpty()){
            //Map the ProductOrigin Entity to ProductOriginGetAllProductDetailsResponseDTO
            List<ProductOriginGetAllProductDetailsResponseDTO> responseDTO =
                    productOriginMapper.productOriginEntityListToProductOriginGetAllProductDetailsResponseDTOList(productOrigins);

            return new PaginatedGetAllOrigins(
                    responseDTO,
                    productOriginRepo.count()
                    );
        }else {
            throw new RuntimeException("No Product Origins Found!");
        }
    }
}
