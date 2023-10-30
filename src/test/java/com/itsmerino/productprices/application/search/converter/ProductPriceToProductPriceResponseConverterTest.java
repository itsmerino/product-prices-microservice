package com.itsmerino.productprices.application.search.converter;

import com.itsmerino.productprices.application.search.dto.ProductPriceResponse;
import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPriceMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductPriceToProductPriceResponseConverterTest {

    private final ProductPriceToProductPriceResponseConverter sut = new ProductPriceToProductPriceResponseConverter();

    @Test
    void itShouldConvertProductPriceToProductPriceResponse() {
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
