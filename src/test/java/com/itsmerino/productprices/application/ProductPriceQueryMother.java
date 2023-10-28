package com.itsmerino.productprices.application;

import java.time.LocalDateTime;
import java.util.Random;

public class ProductPriceQueryMother {

    public static ProductPriceQuery random() {
        Random random = new Random();

        return ProductPriceQuery.builder()
                .productId(random.nextInt())
                .brandId(random.nextInt())
                .date(LocalDateTime.now())
                .build();
    }
}
