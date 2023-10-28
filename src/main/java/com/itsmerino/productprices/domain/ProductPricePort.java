package com.itsmerino.productprices.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPricePort {

    List<ProductPrice> search(Integer productId,
                              Integer brandId,
                              LocalDateTime date);
}
