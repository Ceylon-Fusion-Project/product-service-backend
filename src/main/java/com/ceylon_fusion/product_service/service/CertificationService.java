package com.ceylon_fusion.product_service.service;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.paginated.PaginatedGetAllCertificationsDTO;
import com.ceylon_fusion.product_service.dto.request.CertificateSaveRequestDTO;
import com.ceylon_fusion.product_service.dto.request.CertificationUpdateRequestDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CertificationService {
    CertificationDTO saveCertificate(CertificateSaveRequestDTO certificateSaveRequestDTO);

    PaginatedGetAllCertificationsDTO getCertificationsByProductId(Integer productId, Pageable pageable);

    CertificationDTO updateCertificate(CertificationUpdateRequestDTO certificationUpdateRequestDTO, Integer certificationId);

    String deleteCertificationByID(Integer certificationId);

    PaginatedGetAllCertificationsDTO getAllCertificates(Pageable pageable);
}
