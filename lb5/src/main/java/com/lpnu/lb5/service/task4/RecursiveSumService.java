package com.lpnu.lb5.service.task4;

/**
 * Service to calculate mathematical sum for Variant 17 using different recursive traversal methods.
 *
 * <p>Formula (Variant 17):
 *
 * <pre>
 * S = sum_{i=1}^{N} sin(i) / (1 + cos(i))
 * </pre>
 */
public class RecursiveSumService {

  public static final int MAX_SAFE_DEPTH = 5000;

  /** Term calculation encapsulated. Formula (Variant 17): sin(i) / (1 + cos(i)) */
  private double term(int i) {
    return Math.sin(i) / (1.0 + Math.cos(i));
  }

  private void validateUpperBound(int n) {
    if (n > MAX_SAFE_DEPTH) {
      throw new IllegalArgumentException(
          "Upper bound N exceeds safe recursion depth (" + MAX_SAFE_DEPTH + ")");
    }
  }

  /**
   * Method 1 (Descent, descending): Calculates sum during recursive descent. The argument i
   * decreases from N down to 1.
   *
   * @param n upper bound N
   * @param i current stepping index
   * @param accumulator running sum passed forward
   * @return calculated sum
   */
  public double sumDescentDescending(int n, int i, double accumulator) {
    validateUpperBound(n);
    if (n < 1 || i < 1) {
      return accumulator;
    }
    return sumDescentDescending(n, i - 1, accumulator + term(i));
  }

  /**
   * Method 2 (Descent, ascending): Calculates sum during recursive descent. The argument i
   * increases from 1 up to N.
   *
   * @param n upper bound N
   * @param i current stepping index
   * @param accumulator running sum passed forward
   * @return calculated sum
   */
  public double sumDescentAscending(int n, int i, double accumulator) {
    validateUpperBound(n);
    if (n < 1 || i > n) {
      return accumulator;
    }
    if (i < 1) {
      return sumDescentAscending(n, 1, accumulator);
    }
    return sumDescentAscending(n, i + 1, accumulator + term(i));
  }

  /**
   * Method 3 (Ascent, descending): Calculates sum on return (recursive ascent). The parameter i
   * descends from N down to 1, computing term after traversing.
   *
   * @param n upper bound N
   * @param i current stepping index
   * @return calculated sum
   */
  public double sumAscentDescending(int n, int i) {
    validateUpperBound(n);
    if (n < 1 || i < 1) {
      return 0.0;
    }
    return term(i) + sumAscentDescending(n, i - 1);
  }

  /**
   * Method 4 (Ascent, ascending): Calculates sum on return (recursive ascent). The parameter i
   * ascends from 1 up to N, computing term after traversing.
   *
   * @param n upper bound N
   * @param i current stepping index
   * @return calculated sum
   */
  public double sumAscentAscending(int n, int i) {
    validateUpperBound(n);
    if (n < 1 || i > n) {
      return 0.0;
    }
    if (i < 1) {
      return sumAscentAscending(n, 1);
    }
    return term(i) + sumAscentAscending(n, i + 1);
  }

  /**
   * Method 5 (Iterative): Benchmark loop for validation.
   *
   * @param n upper bound
   * @return sum
   */
  public double sumIterative(int n) {
    validateUpperBound(n);
    if (n < 1) {
      return 0.0;
    }
    double sum = 0.0;
    for (int i = 1; i <= n; i++) {
      sum += term(i);
    }
    return sum;
  }
}
