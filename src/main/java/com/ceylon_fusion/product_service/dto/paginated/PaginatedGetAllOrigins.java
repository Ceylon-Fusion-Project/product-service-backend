package com.ceylon_fusion.product_service.dto.paginated;

import com.ceylon_fusion.product_service.dto.response.ProductOriginGetAllProductDetailsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedGetAllOrigins {
    List<ProductOriginGetAllProductDetailsResponseDTO> origins;
    private long totalOrigins;
}
