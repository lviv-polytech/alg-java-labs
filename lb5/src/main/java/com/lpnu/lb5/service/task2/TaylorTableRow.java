package com.lpnu.lb5.service.task2;

import com.lpnu.common.math.TaylorResult;
import java.util.Optional;

/**
 * Represents a single row in the Taylor series tabulation table for ln(x).
 *
 * @param x argument value
 * @param exactLn exact Math.log(x) value if x > 0, or empty if undefined
 * @param seriesResult Taylor series sum and term count if x > 0.5, or empty if outside domain
 */
public record TaylorTableRow(
    double x, Optional<Double> exactLn, Optional<TaylorResult> seriesResult) {}
