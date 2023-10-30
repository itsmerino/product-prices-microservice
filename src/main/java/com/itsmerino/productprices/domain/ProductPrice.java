package com.itsmerino.productprices.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Currency;

@Builder
@Getter
public class ProductPrice {

    private Integer productId;
    private Integer brandId;
    private Integer priority;
    private Integer rate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private Currency currency;
}
