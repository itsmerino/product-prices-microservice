package com.itsmerino.productprices.infrastructure.rest;

import com.itsmerino.productprices.application.ProductPriceQuery;
import com.itsmerino.productprices.application.ProductPriceResponse;
import com.itsmerino.productprices.application.SearchProductPriceHandler;
import com.itsmerino.productprices.shared.ApiRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.itsmerino.productprices.shared.Constants.*;

@RestController
@RequestMapping(ApiRoutes.PRODUCT_PRICES_V1)
public class ProductPriceController {

    private final SearchProductPriceHandler searchProductPriceHandler;

    @Autowired
    public ProductPriceController(SearchProductPriceHandler searchProductPriceHandler) {
        this.searchProductPriceHandler = searchProductPriceHandler;
    }

    @GetMapping
    public ResponseEntity<ProductPriceResponse> search(@RequestParam Integer productId,
                                                       @RequestParam Integer brandId,
                                                       @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime date) {
        ProductPriceQuery productPriceQuery = buildProductPriceQuery(productId, brandId, date);
        ProductPriceResponse productPriceResponse = searchProductPriceHandler.handle(productPriceQuery);

        return ResponseEntity.ok(productPriceResponse);
    }

    private ProductPriceQuery buildProductPriceQuery(Integer productId,
                                                     Integer brandId,
                                                     LocalDateTime date) {
        return ProductPriceQuery.builder()
                .productId(productId)
                .brandId(brandId)
                .date(date)
                .build();
    }
}
