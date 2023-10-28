package com.itsmerino.prices.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsmerino.prices.PricesApplication;
import com.itsmerino.prices.application.ProductPriceResponse;
import com.itsmerino.prices.config.TestConfiguration;
import com.itsmerino.prices.shared.HttpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = {
                TestConfiguration.class,
                PricesApplication.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class ProductPriceControllerTest {

    private final DateTimeFormatter formatter;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductPriceControllerTest(DateTimeFormatter dateTimeFormatter,
                                      ObjectMapper objectMapper) {
        this.formatter = dateTimeFormatter;
        this.objectMapper = objectMapper;
    }

    @Test
    void itShouldReturnProductPriceResponse() throws IOException, InterruptedException {
        HttpResponse<String> response = HttpUtils.getProductPrice(35455, 1, "2020-06-14-10.00.00");
        ProductPriceResponse productPriceResponse = objectMapper.readValue(response.body(), ProductPriceResponse.class);;

        assertEquals(HttpStatus.OK.value(), response.statusCode());
        assertAll(
                () -> assertEquals(35455, productPriceResponse.getProductId()),
                () -> assertEquals(1, productPriceResponse.getBrandId()),
                () -> assertEquals(1, productPriceResponse.getRate()),
                () -> assertEquals("2020-06-14-00.00.00", productPriceResponse.getStartDate().format(formatter)),
                () -> assertEquals("2020-12-31-23.59.59", productPriceResponse.getEndDate().format(formatter)),
                () -> assertEquals(35.50, productPriceResponse.getPrice()),
                () -> assertEquals("EUR", productPriceResponse.getCurrency().toString())
        );
    }

    @Test
    void itShouldReturnBadRequestResponse() throws IOException, InterruptedException {
        HttpResponse<String> response = HttpUtils.getProductPrice(35455, 1, "BAD_DATE");
        ErrorResponse errorResponse = objectMapper.readValue(response.body(), ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
        assertEquals("Parameter [date] is invalid", errorResponse.getMessage());
    }

    @Test
    void itShouldReturnNotFoundResponse() throws IOException, InterruptedException {
        HttpResponse<String> response = HttpUtils.getProductPrice(1, 1, "2020-06-14-10.00.00");

        assertEquals(HttpStatus.NOT_FOUND.value(), response.statusCode());
        assertTrue(response.body().isEmpty());
    }
}
