package com.itsmerino.prices.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
public class ProductPricesStepDefs {

    private static final String PRODUCTS_URI = "http://localhost/api/v1/products?productId=%d&brandId=%d&date=%s";

    private static final String PRODUCT_ID = "id";
    private static final String BRAND_ID = "brandId";
    private static final String RATE = "rate";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PRICE = "price";
    private static final String CURRENCY = "currency";

    @Given("the following products:")
    public void theFollowingProducts(DataTable dataTable) {
        // TODO: save products
    }

    @When("a customer requests the product {int} of the brand {int} at date {string}")
    public void aCustomerRequestsTheProductOfTheBrandAtDate(Integer productId,
                                                            Integer brandId,
                                                            String dateTime) {
        HttpRequest.newBuilder()
                .uri(URI.create(String.format(PRODUCTS_URI, productId, brandId, dateTime)))
                .GET()
                .build();

        // TODO: get body and map to product
    }

    @Then("the product is returned with the following data:$")
    public void theProductIsReturnedWithTheFollowingData(DataTable dataTable) {
        Map<String, String> productData = dataTable.asMap();

        assertEquals(productData.get(PRODUCT_ID), "productId");
        assertEquals(productData.get(BRAND_ID), "brandId");
        assertEquals(productData.get(RATE), "rate");
        assertEquals(productData.get(START_DATE), "startDate");
        assertEquals(productData.get(END_DATE), "endDate");
        assertEquals(productData.get(PRICE), "price");
        assertEquals(productData.get(CURRENCY), "currency");
    }
}
