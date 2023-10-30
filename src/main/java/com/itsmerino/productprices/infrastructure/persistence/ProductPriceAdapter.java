package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPricePort;
import com.itsmerino.productprices.infrastructure.persistence.converter.ProductPriceEntityToProductPriceConverter;
import com.itsmerino.productprices.infrastructure.persistence.entity.ProductPriceEntity;

import java.util.List;

public class ProductPriceAdapter implements ProductPricePort {

    private final ProductPriceRepository productPriceRepository;
    private final ProductPriceEntityToProductPriceConverter converter;

    public ProductPriceAdapter(ProductPriceRepository productPriceRepository,
                               ProductPriceEntityToProductPriceConverter converter) {
        this.productPriceRepository = productPriceRepository;
        this.converter = converter;
    }

    @Override
    public List<ProductPrice> search(Integer productId,
                                     Integer brandId) {
        List<ProductPriceEntity> products = productPriceRepository.findAllByProductIdAndBrandId(productId, brandId);

        return products
                .stream()
                .map(this::mapToProductPrice)
                .toList();
    }

    private ProductPrice mapToProductPrice(ProductPriceEntity productPriceEntity) {
        return converter.convert(productPriceEntity);
    }
}
