package com.lpnu.lb6.service.task2;

import java.util.Objects;
import java.util.Random;

/**
 * Service providing recursive solutions for Task 6.2 (Variant 17): Finding the index of the maximum
 * even element in a one-dimensional array.
 *
 * <p>Recurrence model: F(A, n, i, k_best) = k_best, if i >= n F(A, n, i, k_best) = F(A, n, i + 1,
 * i), if (a[i] % 2 == 0) and (k_best == -1 or a[i] > a[k_best]) F(A, n, i, k_best) = F(A, n, i + 1,
 * k_best), otherwise
 */
public class RecursiveMaxEvenService {

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
   * Generates pseudo-random integers in range [min, max] recursively.
   *
   * @param array target array
   * @param min inclusive minimum
   * @param max inclusive maximum
   * @param index current index being populated (initial: 0)
   */
  public void generate(int[] array, int min, int max, int index) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (min > max) {
      throw new IllegalArgumentException("Min cannot be greater than max");
    }
    if (index >= array.length) {
      return;
    }
    array[index] = random.nextInt(max - min + 1) + min;
    generate(array, min, max, index + 1);
  }

  /**
   * Formats array elements in a single row recursively with 5-character column width.
   *
   * @param array input array
   * @param index current index (initial: 0)
   * @return formatted row string
   */
  public String formatArray(int[] array, int index) {
    if (array == null || index >= array.length) {
      return "";
    }
    return String.format("%5d", array[index]) + formatArray(array, index + 1);
  }

  /**
   * Finds the zero-based index of the maximum even element recursively starting from index 0.
   *
   * @param array input array
   * @return index of first maximum even element, or -1 if none found
   */
  public int findMaxEvenIndex(int[] array) {
    if (array == null || array.length == 0) {
      return -1;
    }
    return findMaxEvenIndex(array, 0, -1);
  }

  /**
   * Tail-recursive implementation of maximum even index search.
   *
   * @param array input array
   * @param index current index being inspected
   * @param bestIndex index of the best candidate found so far
   * @return index of the first maximum even element, or -1 if none found
   */
  public int findMaxEvenIndex(int[] array, int index, int bestIndex) {
    if (array == null || index >= array.length) {
      return bestIndex;
    }

    int nextBest = bestIndex;
    if (isEven(array[index])) {
      if (bestIndex == -1 || array[index] > array[bestIndex]) {
        nextBest = index;
      }
    }

    return findMaxEvenIndex(array, index + 1, nextBest);
  }
}
