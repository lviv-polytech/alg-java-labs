package com.lpnu.lb4.infra.task1;

import com.lpnu.lb4.service.task1.SeriesSumCalculator;

/**
 * Computes the trigonometric series sum using a do-while loop construct.
 */
class DoWhileSeriesSumCalculator implements SeriesSumCalculator {
    @Override
    public double calculate(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N must be >= 1");
        }
        double sum = 0.0;
        int i = 1;
        do {
            sum += Math.sin(i) / (1 + Math.cos(i));
            i++;
        } while (i <= n);
        return sum;
    }

    @Override
    public String getLoopType() {
        return "do { ... } while(...)";
    }
}
