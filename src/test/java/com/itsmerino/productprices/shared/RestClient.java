package com.itsmerino.productprices.shared;

import com.itsmerino.productprices.infrastructure.rest.ApiRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestClient {

    private static final String LOCALHOST = "http://localhost:";
    private static final String PRODUCT_ID = "productId";
    private static final String BRAND_ID = "brandId";
    private static final String DATE = "date";
    private static final String PRODUCT_PRICE_PARAMS = "?%s";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    private final String apiUrl;

    @Autowired
    public RestClient(@Value("${server.port}") Integer port,
                      @Value("${server.servlet.context-path}") String contextPath) {
        this.apiUrl = LOCALHOST + port + contextPath;
    }

    public HttpResponse<String> getProductPrice(Integer productId,
                                                Integer brandId,
                                                String date) throws IOException, InterruptedException {
        URI uri = buildProductPriceUri(productId, brandId, date);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    private URI buildProductPriceUri(Integer productId,
                                     Integer brandId,
                                     String date) {
        String parameters = String.format(
                PRODUCT_PRICE_PARAMS,
                buildProductPriceParameters(productId, brandId, date)
        );

        String uri = String.format(
                apiUrl + ApiRoutes.PRODUCT_PRICES_V1 + parameters,
                productId,
                brandId,
                date
        );

        return URI.create(uri);
    }

    private String buildProductPriceParameters(Integer productId,
                                               Integer brandId,
                                               String date) {
        Map<String, String> params = new HashMap<>();
        Optional.ofNullable(productId).ifPresent(p -> params.put(PRODUCT_ID, Integer.toString(p)));
        Optional.ofNullable(brandId).ifPresent(b -> params.put(BRAND_ID, Integer.toString(b)));
        Optional.ofNullable(date).ifPresent(d -> params.put(DATE, d));

        return params
                .entrySet()
                .stream()
                .map((entry) -> entry.getKey() + EQUAL + entry.getValue())
                .collect(Collectors.joining(AMPERSAND));
    }
}
