package com.ceylon_fusion.product_service.dto.paginated;

import com.ceylon_fusion.product_service.dto.CertificationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedGetAllCertificationsDTO {
    List<CertificationDTO> certifications;
    private long totalCertifications;
}
