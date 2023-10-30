package com.itsmerino.productprices.infrastructure.rest;

import com.itsmerino.productprices.application.search.SearchProductPriceHandler;
import com.itsmerino.productprices.application.search.dto.ProductPriceQuery;
import com.itsmerino.productprices.application.search.dto.ProductPriceResponse;
import com.itsmerino.productprices.infrastructure.rest.converter.ProductPriceParamsToProductPriceQueryConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.itsmerino.productprices.shared.Constants.DATE_FORMAT;

@RestController
@RequestMapping(ApiRoutes.PRODUCT_PRICES_V1)
public class ProductPriceController {

    private final ProductPriceParamsToProductPriceQueryConverter converter;
    private final SearchProductPriceHandler searchProductPriceHandler;

    public ProductPriceController(ProductPriceParamsToProductPriceQueryConverter converter,
                                  SearchProductPriceHandler searchProductPriceHandler) {
        this.converter = converter;
        this.searchProductPriceHandler = searchProductPriceHandler;
    }

    @GetMapping
    public ResponseEntity<ProductPriceResponse> search(@RequestParam Integer productId,
                                                       @RequestParam Integer brandId,
                                                       @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime date) {
        ProductPriceQuery productPriceQuery = converter.convert(productId, brandId, date);
        ProductPriceResponse productPriceResponse = searchProductPriceHandler.handle(productPriceQuery);

        return ResponseEntity.ok(productPriceResponse);
    }
}
