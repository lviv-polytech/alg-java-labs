package com.lpnu.lb6.service.task4;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Task 6.4: Dynamic Real Array Analysis and In-Place Stable Partition")
class ArrayModifierTest {

  @Nested
  @DisplayName("Iterative Approach Tests")
  class IterativeTests {

    private final IterativeArrayModifier service = new IterativeArrayModifier();

    @Test
    @DisplayName("TC-01: Complex array with two zeros, multiple positives, and stable partition")
    void testTC01_ComplexArray() {
      double[] a = {3.5, 0.0, -2.4, 1.8, 0.0, 4.2, 0.9};
      assertEquals(4, service.countPositiveElements(a));
      assertEquals(4, service.findLastZeroIndex(a));
      assertEquals(5.1, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {0.0, -2.4, 1.8, 0.0, 0.9, 3.5, 4.2};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-02: No zero elements yields sum = 0.0 and stable partition")
    void testTC02_NoZeroElements() {
      double[] a = {2.5, -1.2, 5.0, 1.1};
      assertEquals(3, service.countPositiveElements(a));
      assertEquals(-1, service.findLastZeroIndex(a));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {-1.2, 1.1, 2.5, 5.0};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-03: Last zero at boundary index n - 1 yields sum = 0.0")
    void testTC03_LastZeroAtEnd() {
      double[] a = {4.0, 0.0, 2.5, -3.5, 0.0};
      assertEquals(2, service.countPositiveElements(a));
      assertEquals(4, service.findLastZeroIndex(a));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {0.0, -3.5, 0.0, 4.0, 2.5};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-04: Non-positive array (zero is non-positive) with sum after zero")
    void testTC04_NoPositives() {
      double[] a = {-5.5, -0.1, 0.0, -2.0};
      assertEquals(0, service.countPositiveElements(a));
      assertEquals(2, service.findLastZeroIndex(a));
      assertEquals(-2.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {-5.5, -0.1, 0.0, -2.0};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-05: Critical boundary partition test around trunc(x) <= 1")
    void testTC05_BoundaryValues() {
      double[] a = {2.0, 2.01, 1.99, 1.0, -2.5};
      assertEquals(4, service.countPositiveElements(a));
      assertEquals(-1, service.findLastZeroIndex(a));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {1.99, 1.0, -2.5, 2.0, 2.01};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-06: All elements with trunc(x) > 1 retain initial sequence")
    void testTC06_AllGreaterThanOne() {
      double[] a = {10.5, 20.1, 3.14};
      assertEquals(3, service.countPositiveElements(a));
      assertEquals(-1, service.findLastZeroIndex(a));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {10.5, 20.1, 3.14};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-07: Single zero at index 0 sums all remaining elements")
    void testTC07_ZeroAtStart() {
      double[] a = {0.0, 1.5, -2.5, 3.0};
      assertEquals(2, service.countPositiveElements(a));
      assertEquals(0, service.findLastZeroIndex(a));
      assertEquals(2.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {0.0, 1.5, -2.5, 3.0};
      assertArrayEquals(expected, a, 1e-9);
    }
  }

  @Nested
  @DisplayName("Recursive Approach Tests")
  class RecursiveTests {

    private final RecursiveArrayModifier service = new RecursiveArrayModifier();

    @Test
    @DisplayName("TC-01: Complex array with two zeros recursively")
    void testTC01_ComplexArray() {
      double[] a = {3.5, 0.0, -2.4, 1.8, 0.0, 4.2, 0.9};
      assertEquals(4, service.countPositiveElements(a, 0));
      assertEquals(4, service.findLastZeroIndex(a, a.length - 1));
      assertEquals(5.1, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {0.0, -2.4, 1.8, 0.0, 0.9, 3.5, 4.2};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-02: No zero elements recursively")
    void testTC02_NoZeroElements() {
      double[] a = {2.5, -1.2, 5.0, 1.1};
      assertEquals(3, service.countPositiveElements(a, 0));
      assertEquals(-1, service.findLastZeroIndex(a, a.length - 1));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {-1.2, 1.1, 2.5, 5.0};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-03: Last zero at boundary index n - 1 recursively")
    void testTC03_LastZeroAtEnd() {
      double[] a = {4.0, 0.0, 2.5, -3.5, 0.0};
      assertEquals(2, service.countPositiveElements(a, 0));
      assertEquals(4, service.findLastZeroIndex(a, a.length - 1));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {0.0, -3.5, 0.0, 4.0, 2.5};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-04: Non-positive array recursively")
    void testTC04_NoPositives() {
      double[] a = {-5.5, -0.1, 0.0, -2.0};
      assertEquals(0, service.countPositiveElements(a, 0));
      assertEquals(2, service.findLastZeroIndex(a, a.length - 1));
      assertEquals(-2.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {-5.5, -0.1, 0.0, -2.0};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-05: Boundary partition test recursively")
    void testTC05_BoundaryValues() {
      double[] a = {2.0, 2.01, 1.99, 1.0, -2.5};
      assertEquals(4, service.countPositiveElements(a, 0));
      assertEquals(-1, service.findLastZeroIndex(a, a.length - 1));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {1.99, 1.0, -2.5, 2.0, 2.01};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-06: All elements with trunc(x) > 1 retain sequence recursively")
    void testTC06_AllGreaterThanOne() {
      double[] a = {10.5, 20.1, 3.14};
      assertEquals(3, service.countPositiveElements(a, 0));
      assertEquals(-1, service.findLastZeroIndex(a, a.length - 1));
      assertEquals(0.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {10.5, 20.1, 3.14};
      assertArrayEquals(expected, a, 1e-9);
    }

    @Test
    @DisplayName("TC-07: Single zero at index 0 recursively")
    void testTC07_ZeroAtStart() {
      double[] a = {0.0, 1.5, -2.5, 3.0};
      assertEquals(2, service.countPositiveElements(a, 0));
      assertEquals(0, service.findLastZeroIndex(a, a.length - 1));
      assertEquals(2.0, service.sumAfterLastZero(a), 1e-9);

      service.stableRestructure(a);
      double[] expected = {0.0, 1.5, -2.5, 3.0};
      assertArrayEquals(expected, a, 1e-9);
    }
  }

  @Nested
  @DisplayName("Equivalence Tests")
  class EquivalenceTests {

    private final IterativeArrayModifier itService = new IterativeArrayModifier();
    private final RecursiveArrayModifier recService = new RecursiveArrayModifier();

    @Test
    @DisplayName("Iterative and Recursive methods produce identical results on random array")
    void testEquivalence() {
      double[] original = {2.5, -1.0, 0.0, 3.1, -4.2, 0.0, 5.0, 1.2};
      double[] arrIt = Arrays.copyOf(original, original.length);
      double[] arrRec = Arrays.copyOf(original, original.length);

      assertEquals(
          itService.countPositiveElements(arrIt), recService.countPositiveElements(arrRec, 0));
      assertEquals(itService.sumAfterLastZero(arrIt), recService.sumAfterLastZero(arrRec), 1e-9);

      itService.stableRestructure(arrIt);
      recService.stableRestructure(arrRec);
      assertArrayEquals(arrIt, arrRec, 1e-9);
    }
  }
}
