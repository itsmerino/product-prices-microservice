package com.itsmerino.productprices.domain;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Random;

public class ProductPriceMother {

    public static ProductPrice random() {
        Random random = new Random();

        return ProductPrice.builder()
                .id(random.nextInt())
                .productId(random.nextInt())
                .brandId(random.nextInt())
                .priority(random.nextInt())
                .rate(random.nextInt())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .price(random.nextDouble())
                .currency(Currency.getInstance("EUR"))
                .build();
    }
}
