package com.ceylon_fusion.product_service.service.serviceIMPL;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllCertificationsDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllRatingByProductDetailsDTO;
import com.ceylon_fusion.product_service.dto.request.CertificateSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.CertificationUpdateRequestDTO;
import com.ceylon_fusion.product_service.dto.response.ProductRatingGetAllByProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.Certification;
import com.ceylon_fusion.product_service.entity.Product;
import com.ceylon_fusion.product_service.entity.ProductRating;
import com.ceylon_fusion.product_service.repo.CertificationRepo;
import com.ceylon_fusion.product_service.repo.ProductRepo;
import com.ceylon_fusion.product_service.service.CertificationService;
import com.ceylon_fusion.product_service.util.mappers.CertificationMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationServiceIMPL implements CertificationService {

    @Autowired
    private CertificationRepo certificationRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CertificationMapper certificationMapper;

    @Override
    public CertificationDTO saveCertificate(CertificateSaveRequestDTO certificateSaveRequestDTO) {
        Integer productId = certificateSaveRequestDTO.getProductID();

        if(productRepo.existsById(productId)) {

            Certification newCertification = new Certification(
                    productRepo.findProductByProductIDEquals(productId),
                    certificateSaveRequestDTO.getCertificationName(),
                    certificateSaveRequestDTO.getIssuer(),
                    certificateSaveRequestDTO.getIssuedDate(),
                    certificateSaveRequestDTO.getExpiryDate(),
                    certificateSaveRequestDTO.getCertURL()
            );
            certificationRepo.save(newCertification);

            return certificationMapper.certificationEntityToCertificationDTO(newCertification);
        }else{
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public PaginatedGetAllCertificationsDTO getCertificationsByProductId(Integer productId, Pageable pageRequest) {
        if(certificationRepo.existsByProduct_ProductID(productId)){
            // Get All Certifications by Product ID
            Page<Certification> certifications = certificationRepo
                    .findAllByProductEquals(productRepo.findProductByProductIDEquals(productId), pageRequest);

            // Map Certification Entity List to Certification DTO List
            List<CertificationDTO> certificationList = certificationMapper
                    .certificationEntityListToCertificationDTOList(certifications.getContent());

            for(CertificationDTO certification : certificationList){
                certification.setProductID(productId);
            }

            return new PaginatedGetAllCertificationsDTO(
                    certificationList,
                    certificationRepo.countProductRatingsByProduct_ProductID(productId)
            );
        }else{
            throw new RuntimeException("Product Ratings Not Found");
        }
    }

    @Override
    public CertificationDTO updateCertificate(
            CertificationUpdateRequestDTO certificationUpdateRequestDTO, Integer certificationId)
    {
        if(certificationRepo.existsById(certificationId)) {
            // Get the existing certification
            Certification existingCertification = certificationRepo.getReferenceById(certificationId);

            if(certificationUpdateRequestDTO.getProductID() != null){
                existingCertification.setProduct(
                        productRepo.findProductByProductIDEquals(certificationUpdateRequestDTO.getProductID()));
            }

            if(certificationUpdateRequestDTO.getCertificationName() != null){
                existingCertification.setCertificationName(certificationUpdateRequestDTO.getCertificationName());
            }

            if(certificationUpdateRequestDTO.getIssuer() != null){
                existingCertification.setIssuer(certificationUpdateRequestDTO.getIssuer());
            }

            if(certificationUpdateRequestDTO.getIssuedDate() != null){
                existingCertification.setIssuedDate(certificationUpdateRequestDTO.getIssuedDate());
            }

            if(certificationUpdateRequestDTO.getExpiryDate() != null){
                existingCertification.setExpiryDate(certificationUpdateRequestDTO.getExpiryDate());
            }

            if(certificationUpdateRequestDTO.getCertURL() != null){
                existingCertification.setCertURL(certificationUpdateRequestDTO.getCertURL());
            }

            if(certificationUpdateRequestDTO.getCertActiveState() != existingCertification.getCertActiveState()){
                existingCertification.setCertActiveState(certificationUpdateRequestDTO.getCertActiveState());
            }

            certificationRepo.save(existingCertification);

            return certificationMapper.certificationEntityToCertificationDTO(existingCertification);
        }else{
            throw new RuntimeException("Product Rating Not Found");
        }
    }

    @Override
    public String deleteCertificationByID(Integer certificationId) {
        // check if certificate exists
        if (certificationRepo.existsById(certificationId)) {

            //delete certification by certification id
            certificationRepo.deleteById(certificationId);
            return "Certificate Deleted Successfully!";

        } else {
            throw new RuntimeException("Certificate Not Found");
        }
    }
}
