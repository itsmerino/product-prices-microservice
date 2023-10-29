package com.itsmerino.productprices.infrastructure.rest.converter;

import com.itsmerino.productprices.application.search.dto.ProductPriceQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductPriceParamsToProductPriceQueryConverter {

    public ProductPriceQuery convert(Integer productId,
                                     Integer brandId,
                                     LocalDateTime date) {
        return ProductPriceQuery.builder()
                .productId(productId)
                .brandId(brandId)
                .date(date)
                .build();
    }
}
