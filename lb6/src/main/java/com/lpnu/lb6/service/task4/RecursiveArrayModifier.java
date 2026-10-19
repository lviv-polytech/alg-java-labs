package com.lpnu.lb6.service.task4;

import java.util.Objects;
import java.util.Random;

/**
 * Service providing recursive solutions for Task 6.4: Real dynamic array analysis (positive count,
 * sum after last zero) and in-place stable partition.
 */
public class RecursiveArrayModifier {

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

  /** Recursively generates random real numbers in range [min, max]. */
  public void generate(double[] array, int min, int max, int index) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (index >= array.length) {
      return;
    }
    array[index] = min + (max - min) * random.nextDouble();
    generate(array, min, max, index + 1);
  }

  /** Recursively formats array into a single line string with fixed column width and precision. */
  public String formatArray(double[] array, int index, int fieldWidth, int precision) {
    if (array == null || index >= array.length) {
      return "";
    }
    String formatSpec = "%" + fieldWidth + "." + precision + "f";
    return String.format(formatSpec, array[index])
        + formatArray(array, index + 1, fieldWidth, precision);
  }

  public String formatArray(double[] array, int index) {
    return formatArray(array, index, DEFAULT_FIELD_WIDTH, DEFAULT_PRECISION);
  }

  /** Task 1.1: Counts positive elements (a[i] > 0.0) recursively. */
  public int countPositiveElements(double[] array, int index) {
    if (array == null || index >= array.length) return 0;
    int current = (array[index] > 0.0) ? 1 : 0;
    return current + countPositiveElements(array, index + 1);
  }

  /**
   * Task 1.2 (Step A): Recursively searches for last zero element from right to left (i = n - 1
   * downto 0).
   *
   * @return index in [0, n - 1] or -1 if absent.
   */
  public int findLastZeroIndex(double[] array, int index) {
    if (array == null || index < 0) return -1;
    if (isZero(array[index])) return index;
    return findLastZeroIndex(array, index - 1);
  }

  /** Task 1.2 (Step B): Recursively sums suffix of array starting from index. */
  public double sumFromIndex(double[] array, int index) {
    if (array == null || index >= array.length) return 0.0;
    return array[index] + sumFromIndex(array, index + 1);
  }

  /** Task 1.2: Computes sum of elements after the last zero element recursively. */
  public double sumAfterLastZero(double[] array) {
    if (array == null || array.length == 0) return 0.0;
    int lastZero = findLastZeroIndex(array, array.length - 1);
    if (lastZero == -1 || lastZero >= array.length - 1) {
      return 0.0;
    }
    return sumFromIndex(array, lastZero + 1);
  }

  /** Helper for Task 2: Recursively shifts sub-array a[writePos .. k - 1] one position right. */
  public void shiftRightRecursive(double[] array, int writePos, int k) {
    if (k <= writePos) return;
    array[k] = array[k - 1];
    shiftRightRecursive(array, writePos, k - 1);
  }

  /** Task 2: In-place recursive stable partition. */
  public void stableRestructure(double[] array, int i, int writePos) {
    if (array == null || i >= array.length) return;

    int nextWritePos = writePos;
    if (hasIntPartAtMostOne(array[i])) {
      double temp = array[i];
      shiftRightRecursive(array, writePos, i);
      array[writePos] = temp;
      nextWritePos = writePos + 1;
    }

    stableRestructure(array, i + 1, nextWritePos);
  }

  public void stableRestructure(double[] array) {
    stableRestructure(array, 0, 0);
  }
}
