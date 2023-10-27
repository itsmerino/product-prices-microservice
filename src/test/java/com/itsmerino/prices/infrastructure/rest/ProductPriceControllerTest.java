package com.itsmerino.prices.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.itsmerino.prices.PricesApplication;
import com.itsmerino.prices.application.ProductPriceResponse;
import com.itsmerino.prices.shared.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.itsmerino.prices.shared.Constants.*;
import static com.itsmerino.prices.shared.Constants.PRODUCT_PRICES_PARAMS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PricesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductPriceControllerTest {

    private final DateTimeFormatter formatter;
    private final ObjectMapper objectMapper;

    public ProductPriceControllerTest() {
        this.formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        this.objectMapper = new ObjectMapper().registerModule(
                new JavaTimeModule().addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
        );
    }

    @Test
    void itShouldReturnProductPriceResponse() throws IOException, InterruptedException {
        HttpResponse<String> response = makeGetRequest(35455, "2020-06-14-10.00.00");
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
        HttpResponse<String> response = makeGetRequest(35455, "BAD_DATE");
        ErrorResponse errorResponse = objectMapper.readValue(response.body(), ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
        assertEquals("Parameter [date] is invalid", errorResponse.getMessage());
    }

    @Test
    void itShouldReturnNotFoundResponse() throws IOException, InterruptedException {
        HttpResponse<String> response = makeGetRequest(1, "2020-06-14-10.00.00");

        assertEquals(HttpStatus.NOT_FOUND.value(), response.statusCode());
        assertTrue(response.body().isEmpty());
    }

    private HttpResponse<String> makeGetRequest(Integer productId, String date) throws IOException, InterruptedException {
        URI uri = URI.create(
                String.format(API + BASE_PATH + PRODUCT_PRICES_PATH + PRODUCT_PRICES_PARAMS, productId, 1, date)
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
