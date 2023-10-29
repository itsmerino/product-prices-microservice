package com.itsmerino.productprices.infrastructure.rest;

import com.itsmerino.productprices.domain.ProductPriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(MethodArgumentTypeMismatchException exception) {
        ErrorResponse errorResponse = buildErrorResponse(exception);

        return ResponseEntity.badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound()
                .build();
    }

    private ErrorResponse buildErrorResponse(MethodArgumentTypeMismatchException exception) {
        String message = messageSource.getMessage(
                "bad-request.message",
                new String[]{ exception.getName() },
                LocaleContextHolder.getLocale()
        );

        return ErrorResponse.builder()
                .message(message)
                .build();
    }
}
