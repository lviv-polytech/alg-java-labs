package com.lpnu.lb4.infra;

import com.lpnu.lb4.service.SeriesSumCalculator;

/**
 * Computes the trigonometric series sum using a standard while-loop construct.
 */
class WhileSeriesSumCalculator implements SeriesSumCalculator {
    @Override
    public double calculate(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N must be >= 1");
        }
        double sum = 0.0;
        int i = 1;
        while (i <= n) {
            sum += Math.sin(i) / (1 + Math.cos(i));
            i++;
        }
        return sum;
    }

    @Override
    public String getLoopType() {
        return "while(...) { ... }";
    }
}
