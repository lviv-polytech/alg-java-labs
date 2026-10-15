package com.lpnu.lb6.service.task4;

import java.util.Objects;
import java.util.Random;

/**
 * Service providing iterative solutions for Task 6.4: Real dynamic array analysis (positive count,
 * sum after last zero) and in-place stable partition.
 */
public class IterativeArrayModifier {

  public static final double EPSILON = 1e-9;
  public static final int DEFAULT_FIELD_WIDTH = 8;
  public static final int DEFAULT_PRECISION = 2;
  private final Random random = new Random();

  /** Checks if floating-point value is considered zero within tolerance. */
  public boolean isZero(double x) {
    return Math.abs(x) < EPSILON;
  }

  /** Checks if integer part of x does not exceed 1 (trunc(x) <= 1 <=> x < 2.0). */
  public boolean hasIntPartAtMostOne(double x) {
    return (int) x <= 1;
  }

  /** Generates random real numbers in range [min, max]. */
  public void generate(double[] array, int min, int max) {
    Objects.requireNonNull(array, "Array cannot be null");
    for (int i = 0; i < array.length; i++) {
      array[i] = min + (max - min) * random.nextDouble();
    }
  }

  /** Formats array into a single line string with fixed column width and precision. */
  public String formatArray(double[] array, int fieldWidth, int precision) {
    if (array == null) return "";
    StringBuilder sb = new StringBuilder();
    String formatSpec = "%" + fieldWidth + "." + precision + "f";
    for (double v : array) {
      sb.append(String.format(formatSpec, v));
    }
    return sb.toString();
  }

  public String formatArray(double[] array) {
    return formatArray(array, DEFAULT_FIELD_WIDTH, DEFAULT_PRECISION);
  }

  /** Task 1.1: Counts positive elements (a[i] > 0.0) iteratively. */
  public int countPositiveElements(double[] array) {
    if (array == null) return 0;
    int count = 0;
    for (double v : array) {
      if (v > 0.0) count++;
    }
    return count;
  }

  /**
   * Task 1.2 (Step A): Finds index of the last zero element searching from right to left.
   *
   * @return index in [0, n - 1] or -1 if no zero exists.
   */
  public int findLastZeroIndex(double[] array) {
    if (array == null || array.length == 0) return -1;
    for (int i = array.length - 1; i >= 0; i--) {
      if (isZero(array[i])) {
        return i;
      }
    }
    return -1;
  }

  /** Task 1.2 (Step B): Sums elements located strictly after startIndex. */
  public double sumAfterIndex(double[] array, int startIndex) {
    if (array == null || startIndex < -1 || startIndex >= array.length) {
      return 0.0;
    }
    double sum = 0.0;
    for (int i = startIndex + 1; i < array.length; i++) {
      sum += array[i];
    }
    return sum;
  }

  /**
   * Task 1.2: Computes sum of elements after the last zero element iteratively. Returns 0.0 if no
   * zeros exist or if the last zero is the last element.
   */
  public double sumAfterLastZero(double[] array) {
    int lastZero = findLastZeroIndex(array);
    if (lastZero == -1 || lastZero >= array.length - 1) {
      return 0.0;
    }
    return sumAfterIndex(array, lastZero);
  }

  /**
   * Task 2: In-place stable partition using cyclic shift: elements with trunc(x) <= 1 precede all
   * other elements, preserving relative order.
   */
  public void stableRestructure(double[] array) {
    if (array == null || array.length <= 1) return;
    int writePos = 0;
    for (int i = 0; i < array.length; i++) {
      if (hasIntPartAtMostOne(array[i])) {
        double temp = array[i];
        for (int k = i; k > writePos; k--) {
          array[k] = array[k - 1];
        }
        array[writePos] = temp;
        writePos++;
      }
    }
  }
}
