package com.itsmerino.prices.shared;

public class Constants {

    private Constants() {}

    public static final String API = "http://localhost:8080";
    public static final String BASE_PATH = "/api/v1";
    public static final String PRODUCT_PRICES_PATH = "/product-prices";
    public static final String PRODUCT_PRICES_PARAMS = "?productId=%d&brandId=%d&date=%s";

    public static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    public static final String BAD_REQUEST_MESSAGE = "Parameter [%s] is invalid";
}
