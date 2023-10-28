package com.itsmerino.prices.infrastructure.persistence;

import com.itsmerino.prices.domain.ProductPrice;
import com.itsmerino.prices.domain.ProductPricePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductPriceAdapter implements ProductPricePort {

    private final H2ProductPriceRepository productPriceRepository;

    @Autowired
    public ProductPriceAdapter(H2ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public Optional<ProductPrice> search(Integer productId,
                                         Integer brandId,
                                         LocalDateTime date) {
        return productPriceRepository.findByProductIdAndBrandIdAndDate(productId, brandId, date);
    }
}
