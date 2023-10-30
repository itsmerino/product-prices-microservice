package com.itsmerino.productprices.infrastructure.rest;

import com.itsmerino.productprices.domain.ProductPriceNotFoundException;
import com.itsmerino.productprices.infrastructure.rest.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandler {

    private final ConversionService conversionService;

    @Autowired
    public RestExceptionHandler(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception exception) {
        ErrorResponse errorResponse = conversionService.convert(exception, ErrorResponse.class);

        return ResponseEntity.badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound()
                .build();
    }
}
