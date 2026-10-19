package com.lpnu.lb6.service.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Lab 6 - Subtask 2: Maximum Even Element Search (Variant 17)")
class MaxEvenProcessorTest {

  private final IterativeMaxEvenService itService = new IterativeMaxEvenService();
  private final RecursiveMaxEvenService recService = new RecursiveMaxEvenService();

  @Nested
  @DisplayName("Iterative Search Tests (TC-01 .. TC-09)")
  class IterativeTests {

    @Test
    @DisplayName("TC-01: Base test, odd max 15 ignored, max even 12 at index 3")
    void testTc01() {
      assertEquals(3, itService.findMaxEvenIndex(new int[] {3, 8, 15, 12, 7, 4}));
    }

    @Test
    @DisplayName("TC-02: No even elements present -> returns -1 sentinel")
    void testTc02() {
      assertEquals(-1, itService.findMaxEvenIndex(new int[] {5, -3, 11, 1, 9}));
    }

    @Test
    @DisplayName("TC-03: All even elements negative -> max is -2 at index 3")
    void testTc03() {
      assertEquals(3, itService.findMaxEvenIndex(new int[] {-10, -4, -7, -2, -8}));
    }

    @Test
    @DisplayName("TC-04: Zero is treated as even number (0 > -2) at index 1")
    void testTc04() {
      assertEquals(1, itService.findMaxEvenIndex(new int[] {-5, 0, -2, -9}));
    }

    @Test
    @DisplayName("TC-05: Duplicate maximum even values -> returns first occurrence at index 1")
    void testTc05() {
      assertEquals(1, itService.findMaxEvenIndex(new int[] {6, 14, 9, 14, 2}));
    }

    @Test
    @DisplayName("TC-06: Single even element -> index 0")
    void testTc06() {
      assertEquals(0, itService.findMaxEvenIndex(new int[] {42}));
    }

    @Test
    @DisplayName("TC-07: Single odd element -> returns -1 sentinel")
    void testTc07() {
      assertEquals(-1, itService.findMaxEvenIndex(new int[] {17}));
    }

    @Test
    @DisplayName("TC-08: Maximum at first position (i = 0)")
    void testTc08() {
      assertEquals(0, itService.findMaxEvenIndex(new int[] {20, 4, 2, -6}));
    }

    @Test
    @DisplayName("TC-09: Maximum at last position (i = n - 1)")
    void testTc09() {
      assertEquals(3, itService.findMaxEvenIndex(new int[] {2, 4, 8, 24}));
    }

    @Test
    @DisplayName("Empty or null array returns -1")
    void testEmptyAndNull() {
      assertEquals(-1, itService.findMaxEvenIndex(new int[] {}));
      assertEquals(-1, itService.findMaxEvenIndex(null));
    }
  }

  @Nested
  @DisplayName("Recursive Search Tests (TC-01 .. TC-09)")
  class RecursiveTests {

    @Test
    @DisplayName("TC-01: Base test, odd max 15 ignored, max even 12 at index 3")
    void testTc01() {
      assertEquals(3, recService.findMaxEvenIndex(new int[] {3, 8, 15, 12, 7, 4}));
    }

    @Test
    @DisplayName("TC-02: No even elements present -> returns -1 sentinel")
    void testTc02() {
      assertEquals(-1, recService.findMaxEvenIndex(new int[] {5, -3, 11, 1, 9}));
    }

    @Test
    @DisplayName("TC-03: All even elements negative -> max is -2 at index 3")
    void testTc03() {
      assertEquals(3, recService.findMaxEvenIndex(new int[] {-10, -4, -7, -2, -8}));
    }

    @Test
    @DisplayName("TC-04: Zero is treated as even number (0 > -2) at index 1")
    void testTc04() {
      assertEquals(1, recService.findMaxEvenIndex(new int[] {-5, 0, -2, -9}));
    }

    @Test
    @DisplayName("TC-05: Duplicate maximum even values -> returns first occurrence at index 1")
    void testTc05() {
      assertEquals(1, recService.findMaxEvenIndex(new int[] {6, 14, 9, 14, 2}));
    }

    @Test
    @DisplayName("TC-06: Single even element -> index 0")
    void testTc06() {
      assertEquals(0, recService.findMaxEvenIndex(new int[] {42}));
    }

    @Test
    @DisplayName("TC-07: Single odd element -> returns -1 sentinel")
    void testTc07() {
      assertEquals(-1, recService.findMaxEvenIndex(new int[] {17}));
    }

    @Test
    @DisplayName("TC-08: Maximum at first position (i = 0)")
    void testTc08() {
      assertEquals(0, recService.findMaxEvenIndex(new int[] {20, 4, 2, -6}));
    }

    @Test
    @DisplayName("TC-09: Maximum at last position (i = n - 1)")
    void testTc09() {
      assertEquals(3, recService.findMaxEvenIndex(new int[] {2, 4, 8, 24}));
    }

    @Test
    @DisplayName("Empty or null array returns -1")
    void testEmptyAndNull() {
      assertEquals(-1, recService.findMaxEvenIndex(new int[] {}));
      assertEquals(-1, recService.findMaxEvenIndex(null));
    }
  }

  @Nested
  @DisplayName("Equivalence Verification")
  class EquivalenceTests {

    @Test
    @DisplayName("Iterative and recursive algorithms yield identical index on arbitrary arrays")
    void testEquivalence() {
      int[] array = new int[] {-30, 15, -4, 28, 7, 28, -2, 11};
      assertEquals(itService.findMaxEvenIndex(array), recService.findMaxEvenIndex(array));
    }
  }
}
