package org.lab.lottery.config;

import java.net.http.HttpClient;
import org.lab.lottery.blogic.JavaRandomGenerator;
import org.lab.lottery.blogic.RandomGenerator;
import org.lab.lottery.blogic.RandomOrgRandomGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

@Configuration
public class LotteryConfig {

  @Bean
  @Profile("default")
  RandomGenerator randomGenerator() {
    return new JavaRandomGenerator();
  }

  @Bean
  OkHttp3ClientHttpRequestFactory clientFactory() {
    return new OkHttp3ClientHttpRequestFactory();
  }

  @Bean
  @Profile("randomorg")
  RandomGenerator randomOrgGenerator(HttpClient httpClient) {
    return new RandomOrgRandomGenerator(httpClient);
  }

  @Bean
  @Profile("randomorg")
  HttpClient httpClient() {
    return HttpClient.newHttpClient();
  }
}
