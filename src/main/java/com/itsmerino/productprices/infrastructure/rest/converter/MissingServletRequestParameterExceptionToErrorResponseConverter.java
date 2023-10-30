package com.itsmerino.productprices.infrastructure.rest.converter;

import com.itsmerino.productprices.infrastructure.rest.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Component
public class MissingServletRequestParameterExceptionToErrorResponseConverter implements Converter<MissingServletRequestParameterException, ErrorResponse> {

    public static final String MISSING_PARAMETER_MESSAGE = "missing-parameter.message";

    private final MessageSource messageSource;

    @Autowired
    public MissingServletRequestParameterExceptionToErrorResponseConverter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ErrorResponse convert(MissingServletRequestParameterException exception) {
        String message = messageSource.getMessage(
                MISSING_PARAMETER_MESSAGE,
                new String[]{ exception.getParameterName() },
                LocaleContextHolder.getLocale()
        );

        return ErrorResponse.builder()
                .message(message)
                .build();
    }
}
