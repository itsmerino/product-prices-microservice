package com.itsmerino.productprices.application;

import com.itsmerino.productprices.domain.ProductPriceNotFoundException;
import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPriceMother;
import com.itsmerino.productprices.domain.ProductPricePort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchProductPriceHandlerTest {

    private final ConversionService conversionService = mock(ConversionService.class);
    private final ProductPricePort productPricePort = mock(ProductPricePort.class);
    private final SearchProductPriceHandler sut = new SearchProductPriceHandler(conversionService, productPricePort);

    @Test
    void itShouldReturnProductResponse() {
        ProductPriceQuery productPriceQuery = ProductPriceQueryMother.random();
        ProductPrice productPrice = ProductPriceMother.random();
        ProductPriceResponse productPriceResponse = ProductPriceResponseMother.random();

        when(productPricePort.search(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(List.of(productPrice));
        when(conversionService.convert(productPrice, ProductPriceResponse.class)).thenReturn(productPriceResponse);

        assertEquals(productPriceResponse, sut.handle(productPriceQuery));
    }

    @Test
    void itShouldThrowNotFoundException() {
        when(productPricePort.search(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(List.of());

        assertThrows(ProductPriceNotFoundException.class, () -> sut.handle(ProductPriceQueryMother.random()));
    }
}
