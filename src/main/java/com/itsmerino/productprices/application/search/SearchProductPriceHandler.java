package com.itsmerino.productprices.application.search;

import com.itsmerino.productprices.application.search.converter.ProductPriceToProductPriceResponseConverter;
import com.itsmerino.productprices.application.search.dto.ProductPriceQuery;
import com.itsmerino.productprices.application.search.dto.ProductPriceResponse;
import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPriceNotFoundException;
import com.itsmerino.productprices.domain.ProductPricePort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class SearchProductPriceHandler {

    private final ProductPricePort productPricePort;
    private final ProductPriceToProductPriceResponseConverter converter;

    public SearchProductPriceHandler(ProductPricePort productPricePort,
                                     ProductPriceToProductPriceResponseConverter converter) {
        this.productPricePort = productPricePort;
        this.converter = converter;
    }

    public ProductPriceResponse handle(ProductPriceQuery productPriceQuery) {
        return searchProductPrices(productPriceQuery)
                .stream()
                .filter(p -> isDateBetweenStartDateAndEndDate(productPriceQuery.getDate(), p.getStartDate(), p.getEndDate()))
                .max(Comparator.comparingInt(ProductPrice::getPriority))
                .map(this::mapToProductPriceResponse)
                .orElseThrow(ProductPriceNotFoundException::new);
    }

    private List<ProductPrice> searchProductPrices(ProductPriceQuery productPriceQuery) {
        return productPricePort.search(productPriceQuery.getProductId(), productPriceQuery.getBrandId());
    }

    private boolean isDateBetweenStartDateAndEndDate(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    private ProductPriceResponse mapToProductPriceResponse(ProductPrice productPrice) {
        return converter.convert(productPrice);
    }
}
