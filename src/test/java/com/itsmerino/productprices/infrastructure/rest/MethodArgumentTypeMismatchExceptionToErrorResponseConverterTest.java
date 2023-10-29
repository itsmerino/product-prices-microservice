package com.itsmerino.productprices.infrastructure.rest;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MethodArgumentTypeMismatchExceptionToErrorResponseConverterTest {

    private final MessageSource messageSource = mock(MessageSource.class);
    private final MethodArgumentTypeMismatchExceptionToErrorResponseConverter sut = new MethodArgumentTypeMismatchExceptionToErrorResponseConverter(messageSource);

    @Test
    void itShouldConvertMethodArgumentTypeMismatchExceptionToErrorResponse() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);

        when(exception.getName()).thenReturn("date");
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Parameter [date] is invalid");

        ErrorResponse errorResponse = sut.convert(exception);

        assertNotNull(errorResponse);
        assertEquals("Parameter [date] is invalid", errorResponse.getMessage());
    }
}