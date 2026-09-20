package com.lpnu.common.math;

/**
 * Encapsulates the result of a Taylor series evaluation.
 *
 * @param sum        The accumulated sum of the series
 * @param termsCount The number of terms calculated to reach the required precision
 */
public record TaylorResult(double sum, int termsCount) {
}