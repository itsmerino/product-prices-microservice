package com.itsmerino.productprices.infrastructure.rest.converter;

import com.itsmerino.productprices.infrastructure.rest.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Component
public class MethodArgumentTypeMismatchExceptionToErrorResponseConverter implements Converter<MethodArgumentTypeMismatchException, ErrorResponse> {

    private final MessageSource messageSource;

    @Autowired
    public MethodArgumentTypeMismatchExceptionToErrorResponseConverter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ErrorResponse convert(MethodArgumentTypeMismatchException exception) {
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
