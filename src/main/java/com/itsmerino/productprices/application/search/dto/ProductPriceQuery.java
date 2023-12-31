package com.itsmerino.productprices.application.search.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductPriceQuery {

    private Integer productId;
    private Integer brandId;
    private LocalDateTime date;
}
