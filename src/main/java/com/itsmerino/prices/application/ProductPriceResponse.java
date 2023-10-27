package com.itsmerino.prices.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itsmerino.prices.shared.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceResponse {

    private Integer productId;
    private Integer brandId;
    private Integer rate;
    private @JsonFormat(pattern = Constants.DATE_FORMAT) LocalDateTime startDate;
    private @JsonFormat(pattern = Constants.DATE_FORMAT) LocalDateTime endDate;
    private Double price;
    private Currency currency;
}
