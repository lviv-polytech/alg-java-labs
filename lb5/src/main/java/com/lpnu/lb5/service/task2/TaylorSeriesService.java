package com.lpnu.lb5.service.task2;

import com.lpnu.common.math.TaylorResult;
import com.lpnu.common.math.Variant17Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementing Task 5.2 (Variant 17): Taylor series approximation of ln(x) using separate
 * functions for recurrent term evaluation and series summation.
 *
 * <p>Series expansion: ln(x) = sum_{n=0}^{infinity} ((x - 1)^{n+1}) / ((n + 1) * x^{n+1}), x > 0.5.
 * Recurrence relation: a_0 = (x - 1) / x, a_n = a_{n-1} * ((x - 1) / x) * (n / (n + 1)) for n >= 1.
 */
public class TaylorSeriesService {

  private static final double GRID_EPSILON = 1e-9;

  /**
   * Computes the next Taylor series term a_n from the previous term a_{n-1}.
   *
   * @param x argument x != 0
   * @param n step index n >= 1
   * @param previousTerm value of term a_{n-1}
   * @return value of term a_n
   */
  public double nextTerm(double x, int n, double previousTerm) {
    return Variant17Math.calculateNextTaylorTerm(previousTerm, x, n);
  }

  /**
   * Computes the Taylor series sum for ln(x) and the count of accumulated terms using a do-while
   * loop until |a_n| < epsilon.
   *
   * @param x argument x > 0.5
   * @param epsilon accuracy threshold epsilon > 0
   * @return TaylorResult containing the sum and term count
   */
  public TaylorResult calculateSeries(double x, double epsilon) {
    return Variant17Math.computeLnTaylorSeries(x, epsilon);
  }

  /**
   * Tabulates ln(x) and its Taylor series approximation over [start, end] with step dx. Uses
   * index-based grid generation x_i = start + i * step to avoid floating-point drift.
   *
   * @param start interval start x_start
   * @param end interval end x_end (must be >= start)
   * @param step step size dx (must be > 0)
   * @param epsilon precision threshold eps (must be > 0)
   * @return list of tabulated rows
   */
  public List<TaylorTableRow> tabulate(double start, double end, double step, double epsilon) {
    if (!Double.isFinite(start)
        || !Double.isFinite(end)
        || !Double.isFinite(step)
        || !Double.isFinite(epsilon)) {
      throw new IllegalArgumentException("All tabulation parameters must be finite.");
    }
    if (start > end) {
      throw new IllegalArgumentException("start must be <= end.");
    }
    if (step <= 0.0) {
      throw new IllegalArgumentException("step must be > 0.");
    }
    if (epsilon <= 0.0) {
      throw new IllegalArgumentException("epsilon must be > 0.");
    }

    List<TaylorTableRow> rows = new ArrayList<>();
    for (int i = 0; ; i++) {
      double x = start + i * step;
      if (x > end + GRID_EPSILON) {
        break;
      }
      if (Math.abs(x) < 1e-12) {
        x = 0.0;
      }
      Optional<Double> exactLn = x > 0.0 ? Optional.of(Math.log(x)) : Optional.empty();
      Optional<TaylorResult> series =
          x > 0.5 ? Optional.of(calculateSeries(x, epsilon)) : Optional.empty();
      rows.add(new TaylorTableRow(x, exactLn, series));
    }
    return rows;
  }
}
