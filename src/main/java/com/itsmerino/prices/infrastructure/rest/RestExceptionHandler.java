package com.itsmerino.prices.infrastructure.rest;

import com.itsmerino.prices.domain.ProductPriceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.itsmerino.prices.shared.Constants.BAD_REQUEST_MESSAGE;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .message(String.format(BAD_REQUEST_MESSAGE, exception.getName()))
                        .build()
        );
    }

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }
}
