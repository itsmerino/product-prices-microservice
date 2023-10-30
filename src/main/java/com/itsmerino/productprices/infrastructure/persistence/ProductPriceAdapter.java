package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.domain.ProductPrice;
import com.itsmerino.productprices.domain.ProductPricePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPriceAdapter implements ProductPricePort {

    private final ProductPriceRepository productPriceRepository;

    @Autowired
    public ProductPriceAdapter(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public List<ProductPrice> search(Integer productId,
                                     Integer brandId) {
        return productPriceRepository.findAllByProductIdAndBrandId(productId, brandId);
    }
}
