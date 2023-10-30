package com.itsmerino.productprices.application.search.dto;

import java.time.LocalDateTime;

public class ProductPriceQueryMother {

    public static ProductPriceQuery withDate(LocalDateTime date) {
        return ProductPriceQuery.builder()
                .productId(1)
                .brandId(1)
                .date(date)
                .build();
    }
}
