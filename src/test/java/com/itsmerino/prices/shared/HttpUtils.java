package com.itsmerino.prices.shared;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.itsmerino.prices.shared.Constants.*;
import static com.itsmerino.prices.shared.Constants.PRODUCT_PRICES_PARAMS;

public class HttpUtils {

    public static HttpResponse<String> getProductPrice(Integer productId,
                                                       Integer brandId,
                                                       String date) throws IOException, InterruptedException {
        URI uri = buildUri(productId, brandId, date);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static URI buildUri(Integer productId,
                                Integer brandId,
                                String date) {
        String productPriceUrl = API + BASE_PATH + PRODUCT_PRICES_PATH + PRODUCT_PRICES_PARAMS;
        String url = String.format(productPriceUrl, productId, brandId, date);

        return URI.create(url);
    }
}
