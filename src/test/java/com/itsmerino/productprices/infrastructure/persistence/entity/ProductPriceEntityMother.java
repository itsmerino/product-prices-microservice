package com.itsmerino.productprices.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Random;

public class ProductPriceEntityMother {

    public static ProductPriceEntity random() {
        Random random = new Random();

        return ProductPriceEntity.builder()
                .productId(1)
                .brandId(1)
                .priority(random.nextInt(1))
                .rate(random.nextInt(4))
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .price(random.nextDouble())
                .currency(Currency.getInstance("EUR"))
                .build();
    }
}
