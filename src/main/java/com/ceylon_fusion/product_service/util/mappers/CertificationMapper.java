package com.ceylon_fusion.product_service.util.mappers;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import com.ceylon_fusion.product_service.dto.response.CertificationGetAllProductDetailsResponseDTO;
import com.ceylon_fusion.product_service.entity.Certification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificationMapper {
    List<CertificationDTO> certificationEntityListToCertificationDTOList(List<Certification> certifications);

    List<CertificationGetAllProductDetailsResponseDTO> certificationEntityListToCertificationGetAllProductDetailsResponseDTOList(List<Certification> certifications);
}
