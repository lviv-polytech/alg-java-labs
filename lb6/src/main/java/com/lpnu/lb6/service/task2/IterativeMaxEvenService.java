package com.lpnu.lb6.service.task2;

import java.util.Objects;
import java.util.Random;

/**
 * Service providing iterative solutions for Task 6.2 (Variant 17): Finding the index of the maximum
 * even element in a one-dimensional array.
 *
 * <p>Evenness condition: value % 2 == 0 (valid for positives, negatives, and zero). Tie-breaking:
 * returns the index of the first occurrence if duplicates exist. Sentinel value: returns -1 if no
 * even elements exist.
 */
public class IterativeMaxEvenService {

  public static final int DEFAULT_MIN_VAL = -50;
  public static final int DEFAULT_MAX_VAL = 50;
  private final Random random = new Random();

  /**
   * Checks whether an integer is even.
   *
   * @param value integer value
   * @return true if divisible by 2 without remainder
   */
  public boolean isEven(int value) {
    return value % 2 == 0;
  }

  /**
   * Generates pseudo-random integers in range [min, max] iteratively.
   *
   * @param array target array
   * @param min inclusive minimum
   * @param max inclusive maximum
   */
  public void generate(int[] array, int min, int max) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (min > max) {
      throw new IllegalArgumentException("Min cannot be greater than max");
    }
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(max - min + 1) + min;
    }
  }

  /**
   * Formats array elements in a single row with fixed 5-character column width.
   *
   * @param array input array
   * @return formatted string
   */
  public String formatArray(int[] array) {
    if (array == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (int value : array) {
      sb.append(String.format("%5d", value));
    }
    return sb.toString();
  }

  /**
   * Finds the zero-based index of the maximum even element iteratively.
   *
   * <p>Mathematical model: k_{-1} = -1; k_i = i if (a[i] % 2 == 0) and (k_{i-1} == -1 or a[i] >
   * a[k_{i-1}]); k_i = k_{i-1} otherwise.
   *
   * @param array input array
   * @return index of first maximum even element, or -1 if none found
   */
  public int findMaxEvenIndex(int[] array) {
    if (array == null || array.length == 0) {
      return -1;
    }
    int bestIndex = -1;
    for (int i = 0; i < array.length; i++) {
      if (isEven(array[i])) {
        if (bestIndex == -1 || array[i] > array[bestIndex]) {
          bestIndex = i;
        }
      }
    }
    return bestIndex;
  }
}
