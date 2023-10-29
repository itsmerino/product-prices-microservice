package com.itsmerino.productprices.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class RestClient {

    private static final String LOCALHOST = "http://localhost:";
    private static final String PRODUCT_PRICE_PARAMS = "?productId=%d&brandId=%d&date=%s";

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
        String uri = String.format(
                apiUrl + ApiRoutes.PRODUCT_PRICES_V1 + PRODUCT_PRICE_PARAMS,
                productId,
                brandId,
                date
        );

        return URI.create(uri);
    }
}
