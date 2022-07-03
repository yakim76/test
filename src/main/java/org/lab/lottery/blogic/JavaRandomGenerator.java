package org.lab.lottery.blogic;

import java.util.Random;

public class JavaRandomGenerator implements RandomGenerator {
    @Override
    public int randomInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max + 1) - min) + min;
    }
}
