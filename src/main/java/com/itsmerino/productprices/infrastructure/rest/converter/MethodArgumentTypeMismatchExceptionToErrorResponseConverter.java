package com.itsmerino.productprices.infrastructure.rest.converter;

import com.itsmerino.productprices.infrastructure.rest.dto.ErrorResponse;
import com.itsmerino.productprices.shared.Converter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class MethodArgumentTypeMismatchExceptionToErrorResponseConverter implements Converter<MethodArgumentTypeMismatchException, ErrorResponse> {

    public static final String INVALID_PARAMETER_MESSAGE = "invalid-parameter.message";

    private final MessageSource messageSource;

    public MethodArgumentTypeMismatchExceptionToErrorResponseConverter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ErrorResponse convert(MethodArgumentTypeMismatchException exception) {
        String message = messageSource.getMessage(
                INVALID_PARAMETER_MESSAGE,
                new String[]{ exception.getName() },
                LocaleContextHolder.getLocale()
        );

        return ErrorResponse.builder()
                .message(message)
                .build();
    }
}
