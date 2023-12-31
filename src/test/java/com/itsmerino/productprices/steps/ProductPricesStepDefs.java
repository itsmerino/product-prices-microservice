package com.itsmerino.productprices.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsmerino.productprices.ProductPricesApplication;
import com.itsmerino.productprices.application.search.dto.ProductPriceResponse;
import com.itsmerino.productprices.infrastructure.config.TestConfiguration;
import com.itsmerino.productprices.shared.RestClient;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {
                TestConfiguration.class,
                ProductPricesApplication.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@CucumberContextConfiguration
public class ProductPricesStepDefs {

    private final DateTimeFormatter formatter;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    private ProductPriceResponse productPriceResponse;

    public ProductPricesStepDefs(DateTimeFormatter dateTimeFormatter,
                                 ObjectMapper objectMapper,
                                 RestClient restClient) {
        this.formatter = dateTimeFormatter;
        this.objectMapper = objectMapper;
        this.restClient = restClient;
    }

    @When("a customer requests the product {int} of the brand {int} at date {string}")
    public void aCustomerRequestsTheProductOfTheBrandAtDate(Integer productId,
                                                            Integer brandId,
                                                            String dateTime) throws IOException, InterruptedException {
        HttpResponse<String> response = restClient.getProductPrice(productId, brandId, dateTime);
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
