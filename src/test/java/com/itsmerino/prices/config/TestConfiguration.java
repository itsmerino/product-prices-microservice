package com.itsmerino.prices.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.itsmerino.prices.shared.Constants;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableConfigurationProperties
public class TestConfiguration {

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
    }

    @Bean
    public ObjectMapper objectMapper(DateTimeFormatter dateTimeFormatter) {
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(dateTimeFormatter);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }
}
