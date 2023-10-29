package com.itsmerino.productprices.infrastructure.rest;

import com.itsmerino.productprices.application.ProductPriceQuery;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProductPriceParamsToProductPriceQueryConverterTest {

    private final ProductPriceParamsToProductPriceQueryConverter sut = new ProductPriceParamsToProductPriceQueryConverter();

    @Test
    void itShouldConvertProductPriceParamsToProductPriceQuery() {
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime date = LocalDateTime.now();

        ProductPriceQuery productPriceQuery = sut.convert(productId, brandId, date);

        assertAll(
                () -> assertEquals(productId, productPriceQuery.getProductId()),
                () -> assertEquals(brandId, productPriceQuery.getBrandId()),
                () -> assertEquals(date, productPriceQuery.getDate())
        );
    }
}