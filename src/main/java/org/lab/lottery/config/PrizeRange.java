package org.lab.lottery.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
public class PrizeRange {
    private final int min;
    private final int max;

    @Autowired
    public PrizeRange(@Value("${lottery.prize.min}") int min, @Value("${lottery.prize.max}") int max) {
        this.min = min;
        this.max = max;
    }
}
