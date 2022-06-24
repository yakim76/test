package org.lab.lottery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

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

    private static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-100),
                Arguments.of(1),
                Arguments.of(1000));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int v) {
        int i = javaRandomGenerator.randomInRange(v, v);
        then(i).isEqualTo(v);
    }
}