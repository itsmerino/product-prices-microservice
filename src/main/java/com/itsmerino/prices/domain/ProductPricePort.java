package com.itsmerino.prices.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductPricePort {

    Optional<ProductPrice> search(Integer productId,
                                  Integer brandId,
                                  LocalDateTime date);
}
