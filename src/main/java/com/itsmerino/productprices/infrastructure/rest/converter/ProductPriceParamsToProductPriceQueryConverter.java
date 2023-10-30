package com.itsmerino.productprices.infrastructure.rest.converter;

import com.itsmerino.productprices.application.search.dto.ProductPriceQuery;
import com.itsmerino.productprices.shared.ConverterMultiSource;

import java.time.LocalDateTime;

public class ProductPriceParamsToProductPriceQueryConverter implements ConverterMultiSource<Integer, Integer, LocalDateTime, ProductPriceQuery> {

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
