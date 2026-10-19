package com.lpnu.lb5.service.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Task 5.4: Recursive Sum S = sum_{i=1}^N sin(i)/(1+cos(i)) (Variant 17)")
class RecursiveSumServiceTest {

  private final RecursiveSumService service = new RecursiveSumService();

  @Nested
  @DisplayName("Normal Cases")
  class NormalCases {

    @Test
    @DisplayName("N=1 matches analytical reference sin(1)/(1+cos(1)) across all 5 methods")
    void shouldMatchAnalyticalReferenceForN1() {
      int n = 1;
      double expected = Math.sin(1.0) / (1.0 + Math.cos(1.0)); // ~0.5463024898437905

      assertEquals(expected, service.sumIterative(n), 1e-9);
      assertEquals(expected, service.sumDescentDescending(n, n, 0.0), 1e-9);
      assertEquals(expected, service.sumDescentAscending(n, 1, 0.0), 1e-9);
      assertEquals(expected, service.sumAscentDescending(n, n), 1e-9);
      assertEquals(expected, service.sumAscentAscending(n, 1), 1e-9);
    }

    @Test
    @DisplayName("N=3 matches reference ~16.205130162 across all 5 methods")
    void shouldMatchAnalyticalReferenceForN3() {
      int n = 3;
      double expected = 16.205130161670354;

      assertEquals(expected, service.sumIterative(n), 1e-9);
      assertEquals(expected, service.sumDescentDescending(n, n, 0.0), 1e-9);
      assertEquals(expected, service.sumDescentAscending(n, 1, 0.0), 1e-9);
      assertEquals(expected, service.sumAscentDescending(n, n), 1e-9);
      assertEquals(expected, service.sumAscentAscending(n, 1), 1e-9);
    }

    @Test
    @DisplayName("N=5 matches reference ~13.273068001 across all 5 methods")
    void shouldMatchAnalyticalReferenceForN5() {
      int n = 5;
      double expected = 13.273068001170175;

      assertEquals(expected, service.sumIterative(n), 1e-9);
      assertEquals(expected, service.sumDescentDescending(n, n, 0.0), 1e-9);
      assertEquals(expected, service.sumDescentAscending(n, 1, 0.0), 1e-9);
      assertEquals(expected, service.sumAscentDescending(n, n), 1e-9);
      assertEquals(expected, service.sumAscentAscending(n, 1), 1e-9);
    }

    @Test
    @DisplayName("N=10 produces identical results across all 5 methods")
    void shouldProduceIdenticalResultsForN10() {
      int n = 10;
      double expected = service.sumIterative(n);

      assertEquals(expected, service.sumDescentDescending(n, n, 0.0), 1e-9);
      assertEquals(expected, service.sumDescentAscending(n, 1, 0.0), 1e-9);
      assertEquals(expected, service.sumAscentDescending(n, n), 1e-9);
      assertEquals(expected, service.sumAscentAscending(n, 1), 1e-9);
    }
  }

  @Nested
  @DisplayName("Boundary & Invalid Input Cases")
  class BoundaryAndErrorCases {

    @Test
    @DisplayName("N <= 0 returns 0.0 safely across all 5 methods")
    void shouldReturnZeroWhenNIsZeroOrNegative() {
      assertEquals(0.0, service.sumIterative(0), 1e-12);
      assertEquals(0.0, service.sumDescentDescending(0, 0, 0.0), 1e-12);
      assertEquals(0.0, service.sumDescentAscending(0, 1, 0.0), 1e-12);
      assertEquals(0.0, service.sumAscentDescending(0, 0), 1e-12);
      assertEquals(0.0, service.sumAscentAscending(0, 1), 1e-12);
    }

    @Test
    @DisplayName("N > MAX_SAFE_DEPTH throws IllegalArgumentException to prevent stack overflow")
    void shouldThrowWhenNExceedsSafeDepth() {
      int excessiveN = RecursiveSumService.MAX_SAFE_DEPTH + 1;
      assertThrows(IllegalArgumentException.class, () -> service.sumIterative(excessiveN));
      assertThrows(
          IllegalArgumentException.class,
          () -> service.sumDescentDescending(excessiveN, excessiveN, 0.0));
      assertThrows(
          IllegalArgumentException.class, () -> service.sumDescentAscending(excessiveN, 1, 0.0));
      assertThrows(
          IllegalArgumentException.class,
          () -> service.sumAscentDescending(excessiveN, excessiveN));
      assertThrows(IllegalArgumentException.class, () -> service.sumAscentAscending(excessiveN, 1));
    }
  }
}
