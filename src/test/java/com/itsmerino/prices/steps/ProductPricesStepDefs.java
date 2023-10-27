package com.itsmerino.prices.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.itsmerino.prices.PricesApplication;
import com.itsmerino.prices.application.ProductPriceResponse;
import com.itsmerino.prices.shared.Constants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.itsmerino.prices.shared.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(classes = PricesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductPricesStepDefs {

    private final DateTimeFormatter formatter;
    private final ObjectMapper objectMapper;

    private ProductPriceResponse productPriceResponse;

    public ProductPricesStepDefs() {
        this.formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        this.objectMapper = new ObjectMapper().registerModule(
                new JavaTimeModule().addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
        );
    }

    @When("a customer requests the product {int} of the brand {int} at date {string}")
    public void aCustomerRequestsTheProductOfTheBrandAtDate(Integer productId, Integer brandId, String dateTime) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(API + BASE_PATH + PRODUCT_PRICES_PATH + PRODUCT_PRICES_PARAMS, productId, brandId, dateTime)))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        productPriceResponse = objectMapper.readValue(response.body(), ProductPriceResponse.class);
    }

    @Then("the product is returned with the following data:")
    public void theProductIsReturnedWithTheFollowingData(DataTable dataTable) {
        Map<String, String> productData = dataTable.asMap();

        assertEquals(Integer.parseInt(productData.get("productId")), productPriceResponse.getProductId());
        assertEquals(Integer.parseInt(productData.get("brandId")), productPriceResponse.getBrandId());
        assertEquals(Integer.parseInt(productData.get("rate")), productPriceResponse.getRate());
        assertEquals(productData.get("startDate"), productPriceResponse.getStartDate().format(formatter));
        assertEquals(productData.get("endDate"), productPriceResponse.getEndDate().format(formatter));
        assertEquals(Double.parseDouble(productData.get("price")), productPriceResponse.getPrice());
        assertEquals(productData.get("currency"), productPriceResponse.getCurrency().toString());
    }
}
