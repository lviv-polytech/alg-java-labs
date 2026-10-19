package com.lpnu.lb6.service.task1;

import java.util.Objects;
import java.util.Random;

/**
 * Service providing recursive solutions for Task 6.1 (Variant 17).
 *
 * <p>Recurrence models:
 *
 * <ul>
 *   <li>Count: K(i) = 0 if i >= n; chi(r_i, i) + K(i + 1) if i < n.
 *   <li>Sum: S(i) = 0 if i >= n; (chi(r_i, i) ? r_i : 0) + S(i + 1) if i < n.
 *   <li>Zero: if i < n, if isSelected(i, r_i) then r_i = 0, followed by Zero(i + 1).
 * </ul>
 */
public class RecursiveArrayService {

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
   * Populates the array with pseudo-random integers recursively.
   *
   * @param array target array to populate
   * @param min inclusive lower bound
   * @param max inclusive upper bound
   * @param index current index being filled (initial: 0)
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
   * Formats array elements in a single row recursively with 4-character column width.
   *
   * @param array array to format
   * @param index current index (initial: 0)
   * @return formatted row string
   */
  public String formatArray(int[] array, int index) {
    if (array == null || index >= array.length) {
      return "";
    }
    return String.format("%4d", array[index]) + formatArray(array, index + 1);
  }

  /**
   * Computes the number of elements matching the criterion recursively.
   *
   * @param array array of 25 integers
   * @param index current traversal index (initial: 0)
   * @return count of matching elements
   * @throws IllegalArgumentException if array is null or length != 25
   */
  public int countElements(int[] array, int index) {
    validateArray(array);
    if (index >= array.length) {
      return 0;
    }
    int match = isSelected(index, array[index]) ? 1 : 0;
    return match + countElements(array, index + 1);
  }

  /**
   * Computes the cumulative sum of matching elements recursively.
   *
   * @param array array of 25 integers
   * @param index current traversal index (initial: 0)
   * @return total sum of matching elements
   * @throws IllegalArgumentException if array is null or length != 25
   */
  public int sumElements(int[] array, int index) {
    validateArray(array);
    if (index >= array.length) {
      return 0;
    }
    int contribution = isSelected(index, array[index]) ? array[index] : 0;
    return contribution + sumElements(array, index + 1);
  }

  /**
   * Replaces elements satisfying the criterion with zero in-place recursively.
   *
   * <p>Critical rule: Must be executed strictly AFTER countElements and sumElements.
   *
   * @param array array of 25 integers
   * @param index current traversal index (initial: 0)
   * @throws IllegalArgumentException if array is null or length != 25
   */
  public void zeroElements(int[] array, int index) {
    validateArray(array);
    if (index >= array.length) {
      return;
    }
    if (isSelected(index, array[index])) {
      array[index] = 0;
    }
    zeroElements(array, index + 1);
  }

  /**
   * High-level recursive pipeline executing count, sum, and zeroing in proper sequence.
   *
   * @param array array of 25 integers
   * @param index starting index (usually 0)
   * @return ArrayProcessResult holding count and sum
   */
  public ArrayProcessResult processAndModify(int[] array, int index) {
    validateArray(array);
    int count = countElements(array, index);
    int sum = sumElements(array, index);
    zeroElements(array, index);
    return new ArrayProcessResult(count, sum);
  }

  /** Backward-compatible overload for accumulator-based invocation. */
  public ArrayProcessResult processAndModify(
      int[] array, int index, int countAccumulator, int sumAccumulator) {
    return processAndModify(array, index);
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
