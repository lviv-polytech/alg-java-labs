package com.lpnu.common.math;

/**
 * Contains decomposed mathematical utility functions corresponding to Variant 17 tasks to allow
 * reuse across modules.
 */
public final class Variant17Math {

  private static final int MAX_TAYLOR_TERMS = 1_000_000;

  private Variant17Math() {
    // Prevent instantiation
  }

  /**
   * Calculates the value of the next Taylor series term a_n based on the previous term a_{n-1}.
   * Uses the recurrence relation for Variant 17: a_0 = (x - 1) / x, a_n = a_{n-1} * ((x - 1) / x) *
   * (n / (n + 1)) for n >= 1.
   *
   * @param prevTerm the accumulated previous term (a_{n-1})
   * @param x the argument of the function (x != 0)
   * @param n the current step index (n >= 1)
   * @return the calculated value for the current element (a_n)
   * @throws IllegalArgumentException if inputs are non-finite, n < 1, or x == 0
   */
  public static double calculateNextTaylorTerm(double prevTerm, double x, int n) {
    if (!Double.isFinite(prevTerm) || !Double.isFinite(x) || n < 1 || x == 0.0) {
      throw new IllegalArgumentException(
          "Taylor recurrence requires finite arguments, n >= 1, and x != 0.");
    }
    return prevTerm * ((x - 1.0) / x) * ((double) n / (n + 1.0));
  }

  /**
   * Computes the Taylor Series for the natural logarithm function ln(x) for x > 0.5: ln(x) =
   * sum_{n=0}^{infinity} ((x - 1)^{n+1}) / ((n + 1) * x^{n+1}). Uses a post-condition do-while loop
   * that includes the terminal term whose magnitude falls below eps.
   *
   * @param x the argument of the function (x > 0.5)
   * @param eps precision threshold (eps > 0)
   * @return a TaylorResult containing the sum and the evaluated terms count
   * @throws IllegalArgumentException if x <= 0.5, eps <= 0, or inputs are non-finite
   * @throws IllegalStateException if the series fails to converge within MAX_TAYLOR_TERMS
   */
  public static TaylorResult computeLnTaylorSeries(double x, double eps) {
    if (!Double.isFinite(x) || !Double.isFinite(eps) || eps <= 0.0) {
      throw new IllegalArgumentException("x and eps must be finite and eps must be > 0.");
    }
    if (x <= 0.5) {
      throw new IllegalArgumentException("Domain error: x must be > 0.5");
    }

    double addend = (x - 1.0) / x;
    double sum = 0.0;
    int terms = 0;

    do {
      sum += addend;
      terms++;
      if (Math.abs(addend) < eps) {
        break;
      }
      if (terms >= MAX_TAYLOR_TERMS) {
        throw new IllegalStateException("Taylor series iteration limit reached.");
      }
      addend = calculateNextTaylorTerm(addend, x, terms);
    } while (true);

    return new TaylorResult(sum, terms);
  }
}
