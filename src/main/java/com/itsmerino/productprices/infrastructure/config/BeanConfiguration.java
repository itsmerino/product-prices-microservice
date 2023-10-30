package com.itsmerino.productprices.infrastructure.config;

import com.itsmerino.productprices.application.search.SearchProductPriceHandler;
import com.itsmerino.productprices.application.search.converter.ProductPriceToProductPriceResponseConverter;
import com.itsmerino.productprices.domain.ProductPricePort;
import com.itsmerino.productprices.infrastructure.persistence.ProductPriceAdapter;
import com.itsmerino.productprices.infrastructure.persistence.ProductPriceRepository;
import com.itsmerino.productprices.infrastructure.persistence.converter.ProductPriceEntityToProductPriceConverter;
import com.itsmerino.productprices.infrastructure.rest.converter.MethodArgumentTypeMismatchExceptionToErrorResponseConverter;
import com.itsmerino.productprices.infrastructure.rest.converter.MissingServletRequestParameterExceptionToErrorResponseConverter;
import com.itsmerino.productprices.infrastructure.rest.converter.ProductPriceParamsToProductPriceQueryConverter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductPriceToProductPriceResponseConverter productPriceToProductPriceResponseConverter() {
        return new ProductPriceToProductPriceResponseConverter();
    }

    @Bean
    public ProductPriceEntityToProductPriceConverter productPriceEntityToProductPriceConverter() {
        return new ProductPriceEntityToProductPriceConverter();
    }

    @Bean
    public MethodArgumentTypeMismatchExceptionToErrorResponseConverter methodArgumentTypeMismatchExceptionToErrorResponseConverter(MessageSource messageSource) {
        return new MethodArgumentTypeMismatchExceptionToErrorResponseConverter(messageSource);
    }

    @Bean
    public MissingServletRequestParameterExceptionToErrorResponseConverter missingServletRequestParameterExceptionToErrorResponseConverter(MessageSource messageSource) {
        return new MissingServletRequestParameterExceptionToErrorResponseConverter(messageSource);
    }

    @Bean
    public ProductPriceParamsToProductPriceQueryConverter productPriceParamsToProductPriceQueryConverter() {
        return new ProductPriceParamsToProductPriceQueryConverter();
    }

    @Bean
    public SearchProductPriceHandler searchProductPriceHandler(ProductPricePort productPricePort,
                                                               ProductPriceToProductPriceResponseConverter converter) {
        return new SearchProductPriceHandler(productPricePort, converter);
    }

    @Bean
    public ProductPriceAdapter productPriceAdapter(ProductPriceRepository productPriceRepository,
                                                   ProductPriceEntityToProductPriceConverter converter) {
        return new ProductPriceAdapter(productPriceRepository, converter);
    }
}
