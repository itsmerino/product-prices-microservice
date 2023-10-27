package com.itsmerino.prices.application;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Random;

public class ProductPriceResponseMother {

    public static ProductPriceResponse random() {
        Random random = new Random();

        return ProductPriceResponse.builder()
                .productId(random.nextInt())
                .brandId(random.nextInt())
                .rate(random.nextInt())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .price(random.nextDouble())
                .currency(Currency.getInstance("EUR"))
                .build();
    }
}
