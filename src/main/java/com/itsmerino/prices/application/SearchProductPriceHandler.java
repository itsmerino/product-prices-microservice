package com.itsmerino.prices.application;

import com.itsmerino.prices.domain.ProductPrice;
import com.itsmerino.prices.domain.ProductPriceNotFoundException;
import com.itsmerino.prices.domain.ProductPricePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SearchProductPriceHandler {

    private final ConversionService conversionService;
    private final ProductPricePort productPricePort;

    @Autowired
    public SearchProductPriceHandler(ConversionService conversionService,
                                     ProductPricePort productPricePort) {
        this.conversionService = conversionService;
        this.productPricePort = productPricePort;
    }

    public ProductPriceResponse handle(ProductPriceQuery productPriceQuery) {
        return searchProductPrice(productPriceQuery)
                .map(this::mapToProductPriceResponse)
                .orElseThrow(ProductPriceNotFoundException::new);
    }

    private Optional<ProductPrice> searchProductPrice(ProductPriceQuery productPriceQuery) {
        return productPricePort.search(
                productPriceQuery.getProductId(),
                productPriceQuery.getBrandId(),
                productPriceQuery.getDate()
        );
    }

    private ProductPriceResponse mapToProductPriceResponse(ProductPrice productPrice) {
        return conversionService.convert(productPrice, ProductPriceResponse.class);
    }
}
