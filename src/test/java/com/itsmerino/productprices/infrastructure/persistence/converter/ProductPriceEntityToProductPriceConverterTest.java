package com.itsmerino.productprices.infrastructure.persistence.converter;

import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.infrastructure.persistence.entity.ProductPriceEntity;
import com.itsmerino.productprices.infrastructure.persistence.entity.ProductPriceEntityMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductPriceEntityToProductPriceConverterTest {

    private final ProductPriceEntityToProductPriceConverter sut = new ProductPriceEntityToProductPriceConverter();

    @Test
    void itShouldConvertProductPriceEntityToProductPrice() {
        ProductPriceEntity productPriceEntity = ProductPriceEntityMother.random();
        ProductPrice productPrice = sut.convert(productPriceEntity);

        assertNotNull(productPrice);
        assertAll(
                () -> assertEquals(productPriceEntity.getProductId(), productPrice.getProductId()),
                () -> assertEquals(productPriceEntity.getBrandId(), productPrice.getBrandId()),
                () -> assertEquals(productPriceEntity.getRate(), productPrice.getRate()),
                () -> assertEquals(productPriceEntity.getStartDate(), productPrice.getStartDate()),
                () -> assertEquals(productPriceEntity.getEndDate(), productPrice.getEndDate()),
                () -> assertEquals(productPriceEntity.getPrice(), productPrice.getPrice()),
                () -> assertEquals(productPriceEntity.getCurrency(), productPrice.getCurrency())
        );
    }
}