package com.lpnu.lb6.service.task1;

import java.util.Objects;
import java.util.Random;

/**
 * Service providing iterative solutions for Task 6.1 (Variant 17).
 *
 * <p>Domain model: Vector dimension n = 25, range [min, max] = [4, 73]. Criterion: (value % 6 == 0)
 * || (index % 5 != 0).
 *
 * <p>Single responsibility principle: Generation, formatting, counting, summing, and zeroing are
 * strictly partitioned into separate methods. Zeroing is executed after count and sum to avoid data
 * corruption.
 */
public class IterativeArrayService {

  public static final int REQUIRED_SIZE = 25;
  private final Random random = new Random();

  /**
   * Checks if an element satisfies the Variant 17 criterion.
   *
   * @param index 0-based element index
   * @param value element integer value
   * @return true if (value % 6 == 0) or (index % 5 != 0)
   */
  public boolean isSelected(int index, int value) {
    return (value % 6 == 0) || (index % 5 != 0);
  }

  /**
   * Populates the array with pseudo-random integers uniformly distributed in [min, max].
   *
   * @param array target array to populate
   * @param min inclusive lower bound
   * @param max inclusive upper bound
   * @throws IllegalArgumentException if array is null or min > max
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
   * Formats array elements in a single row with fixed 4-character column width.
   *
   * @param array array to format
   * @return formatted string
   */
  public String formatArray(int[] array) {
    if (array == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (int value : array) {
      sb.append(String.format("%4d", value));
    }
    return sb.toString();
  }

  /**
   * Computes the number of elements matching the criterion iteratively.
   *
   * <p>Invariant: 20 <= count <= 25 for any valid array of 25 elements.
   *
   * @param array array of 25 integers
   * @return count of matching elements
   * @throws IllegalArgumentException if array is null or length != 25
   */
  public int countElements(int[] array) {
    validateArray(array);
    int count = 0;
    for (int i = 0; i < array.length; i++) {
      if (isSelected(i, array[i])) {
        count++;
      }
    }
    return count;
  }

  /**
   * Computes the cumulative sum of matching elements iteratively.
   *
   * <p>Invariant: 80 <= sum <= 1825 for any valid array in [4, 73].
   *
   * @param array array of 25 integers
   * @return total sum of matching elements
   * @throws IllegalArgumentException if array is null or length != 25
   */
  public int sumElements(int[] array) {
    validateArray(array);
    int sum = 0;
    for (int i = 0; i < array.length; i++) {
      if (isSelected(i, array[i])) {
        sum += array[i];
      }
    }
    return sum;
  }

  /**
   * Replaces elements satisfying the criterion with zero in-place iteratively.
   *
   * <p>Critical rule: Must be called AFTER countElements and sumElements.
   *
   * @param array array of 25 integers
   * @throws IllegalArgumentException if array is null or length != 25
   */
  public void zeroElements(int[] array) {
    validateArray(array);
    for (int i = 0; i < array.length; i++) {
      if (isSelected(i, array[i])) {
        array[i] = 0;
      }
    }
  }

  /**
   * High-level pipeline method executing count, sum, and zeroing in proper sequence.
   *
   * @param array array of 25 integers
   * @return ArrayProcessResult holding count and sum
   */
  public ArrayProcessResult processAndModify(int[] array) {
    validateArray(array);
    int count = countElements(array);
    int sum = sumElements(array);
    zeroElements(array);
    return new ArrayProcessResult(count, sum);
  }

  private void validateArray(int[] array) {
    if (array == null) {
      throw new IllegalArgumentException("Array cannot be null");
    }
    if (array.length != REQUIRED_SIZE) {
      throw new IllegalArgumentException("Array must have exactly " + REQUIRED_SIZE + " elements");
    }
  }
}
