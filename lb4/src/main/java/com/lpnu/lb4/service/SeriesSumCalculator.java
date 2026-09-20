package com.lpnu.lb4.service;

/**
 * Contract for a mathematical calculator that computes the finite sum of a trigonometric series.
 * S = Sum(sin(i) / (1 + cos(i))) for i=1 to N.
 */
public interface SeriesSumCalculator {

    /**
     * Calculates the series sum for a given upper bound N.
     *
     * @param n the upper bound for the summation
     * @return the sum of the series
     * @throws IllegalArgumentException if n is less than 1
     */
    double calculate(int n);

    /**
     * Returns the name of the loop algorithm used in this implementation (e.g. "while", "do-while").
     *
     * @return a descriptive name of the loop type
     */
    String getLoopType();
}
