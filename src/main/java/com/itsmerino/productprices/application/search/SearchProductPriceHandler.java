package com.itsmerino.productprices.application.search;

import com.itsmerino.productprices.application.search.dto.ProductPriceQuery;
import com.itsmerino.productprices.application.search.dto.ProductPriceResponse;
import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPriceNotFoundException;
import com.itsmerino.productprices.domain.ProductPricePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

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
        return searchProductPrices(productPriceQuery)
                .stream()
                .max(Comparator.comparingInt(ProductPrice::getPriority))
                .map(this::mapToProductPriceResponse)
                .orElseThrow(ProductPriceNotFoundException::new);
    }

    private List<ProductPrice> searchProductPrices(ProductPriceQuery productPriceQuery) {
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
