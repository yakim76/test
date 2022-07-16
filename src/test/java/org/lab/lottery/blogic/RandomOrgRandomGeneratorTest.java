package org.lab.lottery.blogic;

import static org.assertj.core.api.BDDAssertions.then;

import java.net.http.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomOrgRandomGeneratorTest {

  private RandomOrgRandomGenerator randomGenerator;

  @BeforeEach
  void setUp() {
    randomGenerator = new RandomOrgRandomGenerator(HttpClient.newHttpClient());
  }

  @Test
  void randomInRange() {
    int i = randomGenerator.randomInRange(1, 1000);
    then(i).isBetween(1, 1000);
  }
}