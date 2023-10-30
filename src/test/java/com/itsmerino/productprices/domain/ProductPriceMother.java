package com.itsmerino.productprices.domain;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Random;

public class ProductPriceMother {

    public static ProductPrice random() {
        Random random = new Random();

        return buildCommonFields()
                .priority(random.nextInt(1))
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build();
    }

    public static ProductPrice withPriorityAndDates(Integer priority, LocalDateTime startDate, LocalDateTime endDate) {
        return buildCommonFields()
                .priority(priority)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    private static ProductPrice.ProductPriceBuilder buildCommonFields() {
        Random random = new Random();

        return ProductPrice.builder()
                .productId(1)
                .brandId(1)
                .rate(random.nextInt(4))
                .price(random.nextDouble())
                .currency(Currency.getInstance("EUR"));
    }
}
