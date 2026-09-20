package com.lpnu.lb4.infra.task1;

import com.lpnu.lb4.service.task1.SeriesSumCalculator;

/**
 * Computes the trigonometric series sum using a standard for-loop with decrementing iterator (reverse).
 */
class ForDecrementSeriesSumCalculator implements SeriesSumCalculator {
    @Override
    public double calculate(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N must be >= 1");
        }
        double sum = 0.0;
        for (int i = n; i >= 1; i--) {
            sum += Math.sin(i) / (1 + Math.cos(i));
        }
        return sum;
    }

    @Override
    public String getLoopType() {
        return "for(i = n; i >= 1; i--)";
    }
}
