package com.itsmerino.productprices.application.search;

import com.itsmerino.productprices.application.search.converter.ProductPriceToProductPriceResponseConverter;
import com.itsmerino.productprices.application.search.dto.ProductPriceQuery;
import com.itsmerino.productprices.application.search.dto.ProductPriceQueryMother;
import com.itsmerino.productprices.application.search.dto.ProductPriceResponse;
import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPriceMother;
import com.itsmerino.productprices.domain.ProductPriceNotFoundException;
import com.itsmerino.productprices.domain.ProductPricePort;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchProductPriceHandlerTest {

    private final ProductPricePort productPricePort = mock(ProductPricePort.class);
    private final ProductPriceToProductPriceResponseConverter converter = new ProductPriceToProductPriceResponseConverter();
    private final SearchProductPriceHandler sut = new SearchProductPriceHandler(productPricePort, converter);


    @Test
    void itShouldReturnAppropriateProductPriceResponse() {
        ProductPriceQuery productPriceQuery = ProductPriceQueryMother.withDate(LocalDateTime.of(2023, 10, 13, 0, 0));
        ProductPrice productPriceOne = ProductPriceMother.withPriorityAndDates(
                0,
                LocalDateTime.of(2023, 10, 1, 0, 0),
                LocalDateTime.of(2023, 10, 15, 0, 0)
        );
        ProductPrice productPriceTwo = ProductPriceMother.withPriorityAndDates(
                1,
                LocalDateTime.of(2023, 10, 12, 0, 0),
                LocalDateTime.of(2023, 10, 30, 0, 0)
        );

        when(productPricePort.search(anyInt(), anyInt())).thenReturn(List.of(productPriceOne, productPriceTwo));

        ProductPriceResponse productPriceResponse = sut.handle(productPriceQuery);

        assertAll(
                () -> assertEquals(productPriceTwo.getProductId(), productPriceResponse.getProductId()),
                () -> assertEquals(productPriceTwo.getBrandId(), productPriceResponse.getBrandId()),
                () -> assertEquals(productPriceTwo.getStartDate(), productPriceResponse.getStartDate()),
                () -> assertEquals(productPriceTwo.getEndDate(), productPriceResponse.getEndDate())
        );
    }

    @Test
    void itShouldThrowProductPriceNotFoundException() {
        ProductPriceQuery productPriceQuery = ProductPriceQueryMother.withDate(LocalDateTime.of(2023, 10, 30, 0, 0));
        ProductPrice productPrice = ProductPriceMother.withPriorityAndDates(
                0,
                LocalDateTime.of(2023, 10, 1, 0, 0),
                LocalDateTime.of(2023, 10, 15, 0, 0)
        );

        when(productPricePort.search(anyInt(), anyInt())).thenReturn(List.of(productPrice));

        assertThrows(ProductPriceNotFoundException.class, () -> sut.handle(productPriceQuery));
    }
}
