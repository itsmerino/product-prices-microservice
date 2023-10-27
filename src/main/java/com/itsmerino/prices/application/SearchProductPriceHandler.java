package com.itsmerino.prices.application;

import com.itsmerino.prices.domain.ProductPriceNotFoundException;
import com.itsmerino.prices.domain.ProductPricePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class SearchProductPriceHandler {

    private final ConversionService conversionService;
    private final ProductPricePort productPricePort;

    @Autowired
    public SearchProductPriceHandler(ConversionService conversionService, ProductPricePort productPricePort) {
        this.conversionService = conversionService;
        this.productPricePort = productPricePort;
    }

    public ProductPriceResponse handle(ProductPriceQuery query) {
        return productPricePort.search(query.getProductId(), query.getBrandId(), query.getDate())
                .map(p -> conversionService.convert(p, ProductPriceResponse.class))
                .orElseThrow(ProductPriceNotFoundException::new);
    }
}
