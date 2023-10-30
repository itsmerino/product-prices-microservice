package com.itsmerino.productprices.infrastructure.rest.converter;

import com.itsmerino.productprices.infrastructure.rest.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MissingServletRequestParameterExceptionToErrorResponseConverterTest {

    private final MessageSource messageSource = mock(MessageSource.class);
    private final MissingServletRequestParameterExceptionToErrorResponseConverter sut = new MissingServletRequestParameterExceptionToErrorResponseConverter(messageSource);

    @Test
    void itShouldConvertMissingServletRequestParameterExceptionToErrorResponse() {
        String message = "Parameter [productId] is missing";
        MissingServletRequestParameterException exception = mock(MissingServletRequestParameterException.class);

        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn(message);

        ErrorResponse errorResponse = sut.convert(exception);

        assertNotNull(errorResponse);
        assertEquals(message, errorResponse.getMessage());
    }
}