package com.itsmerino.productprices.application.search.converter;

import com.itsmerino.productprices.application.search.dto.ProductPriceResponse;
import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.shared.Converter;

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
