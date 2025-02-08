package com.ceylon_fusion.product_service.dto.paginated;

import com.ceylon_fusion.product_service.dto.response.ProductGetAllResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedGetAllProductResponseDTO {
    List<ProductGetAllResponseDTO> productGetAllResponseDTOS;
    private long totalItems;
}
