package org.lab.lottery.blogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lab.lottery.blogic.RandomOrgRandomGenerator;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import static org.assertj.core.api.BDDAssertions.then;

class RandomOrgRandomGeneratorTest {
    private RandomOrgRandomGenerator randomGenerator;

    @BeforeEach
    void setUp() {
        randomGenerator = new RandomOrgRandomGenerator(new OkHttp3ClientHttpRequestFactory());
    }

    @Test
    void randomInRange() {
        int i = randomGenerator.randomInRange(1, 1000);
        then(i).isBetween(1, 1000);
    }
}