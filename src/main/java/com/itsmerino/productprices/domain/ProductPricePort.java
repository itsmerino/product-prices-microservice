package com.itsmerino.productprices.domain;

import java.util.List;

public interface ProductPricePort {

    List<ProductPrice> search(Integer productId,
                              Integer brandId);
}
