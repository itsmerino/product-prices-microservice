package com.itsmerino.productprices.infrastructure.rest;

import com.itsmerino.productprices.domain.ProductPriceNotFoundException;
import com.itsmerino.productprices.infrastructure.rest.converter.MethodArgumentTypeMismatchExceptionToErrorResponseConverter;
import com.itsmerino.productprices.infrastructure.rest.converter.MissingServletRequestParameterExceptionToErrorResponseConverter;
import com.itsmerino.productprices.infrastructure.rest.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandler {

    private final MethodArgumentTypeMismatchExceptionToErrorResponseConverter invalidParamConverter;
    private final MissingServletRequestParameterExceptionToErrorResponseConverter missingParamConverter;

    public RestExceptionHandler(MethodArgumentTypeMismatchExceptionToErrorResponseConverter invalidParamConverter,
                                MissingServletRequestParameterExceptionToErrorResponseConverter missingParamConverter) {
        this.invalidParamConverter = invalidParamConverter;
        this.missingParamConverter = missingParamConverter;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(MethodArgumentTypeMismatchException exception) {
        ErrorResponse errorResponse = invalidParamConverter.convert(exception);

        return ResponseEntity.badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(MissingServletRequestParameterException exception) {
        ErrorResponse errorResponse = missingParamConverter.convert(exception);

        return ResponseEntity.badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound()
                .build();
    }
}
