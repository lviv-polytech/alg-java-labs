package com.lpnu.lb4.service.task6;

/**
 * Contract for a mathematical calculator that computes a nested sum expression.
 */
public interface NestedSeriesCalculator {

    /**
     * Calculates the complex nested series sum for the static bound (k=20).
     *
     * @return the computed sum of the nested series
     */
    double calculate();

    /**
     * Returns the name of the loop algorithm pattern used (e.g., "nested-while").
     *
     * @return loop type descriptor
     */
    String getAlgorithmName();
}