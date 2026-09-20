package com.lpnu.lb4.infra.task1;

import com.lpnu.lb4.service.task1.SeriesSumCalculator;

/**
 * Computes the trigonometric series sum using a standard for-loop with incrementing iterator.
 */
class ForIncrementSeriesSumCalculator implements SeriesSumCalculator {
    @Override
    public double calculate(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N must be >= 1");
        }
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += Math.sin(i) / (1 + Math.cos(i));
        }
        return sum;
    }

    @Override
    public String getLoopType() {
        return "for(i = 1; i <= n; i++)";
    }
}
