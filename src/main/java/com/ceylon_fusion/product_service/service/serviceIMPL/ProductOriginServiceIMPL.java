package com.ceylon_fusion.product_service.service.serviceIMPL;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.ProductOriginDTO;
import com.ceylon_fusion.product_service.dto.request.ProductOriginSaveAndUpdateRequestDTO;
import com.ceylon_fusion.product_service.entity.ProductOrigin;
import com.ceylon_fusion.product_service.repo.ProductOriginRepo;
import com.ceylon_fusion.product_service.repo.ProductRepo;
import com.ceylon_fusion.product_service.service.ProductOriginService;
import com.ceylon_fusion.product_service.util.mappers.ProductOriginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public CertificationDTO updateOrigin(ProductOriginSaveAndUpdateRequestDTO updateRequestDTO, Integer originId) {

        if (productOriginRepo.existsById(originId)) {

            ProductOrigin existingproductOrigin = productOriginRepo.getReferenceById(originId);

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

            return productOriginMapper.productOriginEntityToCertificationDTO(existingproductOrigin);
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
            ProductOrigin productOrigin = productOriginRepo.getReferenceById(originId);
            return productOriginMapper.productOriginEntityToProductOriginDTO(productOrigin);
        } else {
            throw new RuntimeException("Product Origin Not Found!");
        }
    }
}
