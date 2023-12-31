package com.itsmerino.productprices.infrastructure.persistence.converter;

import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.infrastructure.persistence.entity.ProductPriceEntity;
import com.itsmerino.productprices.shared.Converter;

public class ProductPriceEntityToProductPriceConverter implements Converter<ProductPriceEntity, ProductPrice> {

    @Override
    public ProductPrice convert(ProductPriceEntity productPriceEntity) {
        return ProductPrice.builder()
                .productId(productPriceEntity.getProductId())
                .brandId(productPriceEntity.getBrandId())
                .priority(productPriceEntity.getPriority())
                .rate(productPriceEntity.getRate())
                .startDate(productPriceEntity.getStartDate())
                .endDate(productPriceEntity.getEndDate())
                .price(productPriceEntity.getPrice())
                .currency(productPriceEntity.getCurrency())
                .build();
    }
}
