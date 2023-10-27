package com.itsmerino.prices.application;

import com.itsmerino.prices.domain.ProductPrice;
import com.itsmerino.prices.domain.ProductPriceMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductPriceToProductPriceResponseConverterTest {

    private final ProductPriceToProductPriceResponseConverter sut = new ProductPriceToProductPriceResponseConverter();

    @Test
    void itShouldConvertProductPriceToProductResponse() {
        ProductPrice productPrice = ProductPriceMother.random();
        ProductPriceResponse productPriceResponse = sut.convert(productPrice);

        assertNotNull(productPriceResponse);
        assertAll(
                () -> assertEquals(productPrice.getProductId(), productPriceResponse.getProductId()),
                () -> assertEquals(productPrice.getBrandId(), productPriceResponse.getBrandId()),
                () -> assertEquals(productPrice.getRate(), productPriceResponse.getRate()),
                () -> assertEquals(productPrice.getStartDate(), productPriceResponse.getStartDate()),
                () -> assertEquals(productPrice.getEndDate(), productPriceResponse.getEndDate()),
                () -> assertEquals(productPrice.getPrice(), productPriceResponse.getPrice()),
                () -> assertEquals(productPrice.getCurrency(), productPriceResponse.getCurrency())
        );
    }
}
