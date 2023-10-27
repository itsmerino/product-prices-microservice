package com.itsmerino.prices.shared;

public class Constants {

    private Constants() {}

    public static final String API = "http://localhost:8080";
    public static final String BASE_PATH = "/api/v1";
    public static final String PRODUCT_PRICES_PATH = "/product-prices";
    public static final String PRODUCT_PRICES_PARAMS = "?productId=%d&brandId=%d&date=%s";
    public static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    public static final String PRODUCT_ID = "id";
    public static final String BRAND_ID = "brandId";
    public static final String RATE = "rate";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String PRICE = "price";
    public static final String CURRENCY = "currency";
}
