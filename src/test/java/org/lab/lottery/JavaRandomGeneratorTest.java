package org.lab.lottery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.*;

class JavaRandomGeneratorTest {
    private JavaRandomGenerator javaRandomGenerator;

    @BeforeEach
    void setUp() {
        javaRandomGenerator = new JavaRandomGenerator();
    }

    @Test
    void randomInRange() {
        int min = 0;
        int max = 1000;
        Set<Integer> res = new HashSet<>(100);
        for (int i = 0; i < 100; i++) {
            res.add(javaRandomGenerator.randomInRange(min, max));
        }
        then(res.isEmpty()).isFalse();
        then(res.size()).isGreaterThan(10);
    }
}