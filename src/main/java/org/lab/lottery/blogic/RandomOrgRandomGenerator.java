package org.lab.lottery.blogic;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class RandomOrgRandomGenerator implements RandomGenerator {

  private static final String URL_TEMPLATE = "https://www.random.org/integers/?num=1&min=%d&max=%d&col=1&base=10&format=plain&rnd=new";
  private final HttpClient httpClient;

  public RandomOrgRandomGenerator(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public int randomInRange(int min, int max) {
    var request = HttpRequest.newBuilder()
        .uri(URI.create(String.format(URL_TEMPLATE, min, max)))
        .build();
    try {
      var response = httpClient.send(request, BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return Integer.parseInt(response.body().trim());
      } else {
        throw new RuntimeException();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
