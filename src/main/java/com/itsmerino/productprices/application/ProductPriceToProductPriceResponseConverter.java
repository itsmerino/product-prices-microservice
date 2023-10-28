package com.itsmerino.productprices.application;

import com.itsmerino.productprices.domain.ProductPrice;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceToProductPriceResponseConverter implements Converter<ProductPrice, ProductPriceResponse> {

    @Override
    public ProductPriceResponse convert(ProductPrice productPrice) {
        return ProductPriceResponse.builder()
                .productId(productPrice.getProductId())
                .brandId(productPrice.getBrandId())
                .rate(productPrice.getRate())
                .startDate(productPrice.getStartDate())
                .endDate(productPrice.getEndDate())
                .price(productPrice.getPrice())
                .currency(productPrice.getCurrency())
                .build();
    }
}
