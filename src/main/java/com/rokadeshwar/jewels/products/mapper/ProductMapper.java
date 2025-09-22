package com.rokadeshwar.jewels.products.mapper;

import com.rokadeshwar.jewels.products.domain.Product;
import com.rokadeshwar.jewels.products.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product p) {
        return new ProductDto(
                p.getId(),
                p.getTitle(),
                p.getBaseType(),
                p.getPrice(),
                p.getImageUrl(),
                p.getImages(),
                p.getSpecs(),
                p.getHighlights(),
                p.getBestSeller()
        );
    }
}
