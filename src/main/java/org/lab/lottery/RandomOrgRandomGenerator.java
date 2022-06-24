package org.lab.lottery;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RandomOrgRandomGenerator implements RandomGenerator {
    private static final String URL_TEMPLATE = "https://www.random.org/integers/?num=1&min=%d&max=%d&col=1&base=10&format=plain&rnd=new";
    private final OkHttp3ClientHttpRequestFactory clientFactory;

    public RandomOrgRandomGenerator(OkHttp3ClientHttpRequestFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public int randomInRange(int min, int max) {
        ClientHttpRequest request;
        try {
            request = clientFactory.createRequest(
                    new URI(String.format(URL_TEMPLATE, min, max)),
                    HttpMethod.GET
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            String s;
            try (ClientHttpResponse execute = request.execute()) {
                s = (new String(execute.getBody().readAllBytes())).trim();
            }
            return Integer.parseInt(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
