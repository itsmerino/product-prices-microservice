package com.itsmerino.productprices.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.itsmerino.productprices.shared.Constants;
import com.itsmerino.productprices.shared.RestClient;
import org.springframework.beans.factory.annotation.Value;
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

    @Bean
    public RestClient restClient(@Value("${server.port}") Integer port,
                                 @Value("${server.servlet.context-path}") String contextPath) {
        return new RestClient(port, contextPath);
    }
}
