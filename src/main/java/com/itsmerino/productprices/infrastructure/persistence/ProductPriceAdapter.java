package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPricePort;
import com.itsmerino.productprices.infrastructure.persistence.entity.ProductPriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPriceAdapter implements ProductPricePort {

    private final ConversionService conversionService;
    private final ProductPriceRepository productPriceRepository;

    @Autowired
    public ProductPriceAdapter(ConversionService conversionService,
                               ProductPriceRepository productPriceRepository) {
        this.conversionService = conversionService;
        this.productPriceRepository = productPriceRepository;
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
        return conversionService.convert(productPriceEntity, ProductPrice.class);
    }
}
