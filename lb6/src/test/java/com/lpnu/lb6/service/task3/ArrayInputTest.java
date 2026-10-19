package com.lpnu.lb6.service.task3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName(
    "Task 6.3: Stream and Keyboard Array Input with Ordinary and Generic/Template Functions")
class ArrayInputTest {

  @Nested
  @DisplayName("Iterative Approach Tests")
  class IterativeTests {

    private final IterativeInputService service = new IterativeInputService();

    @Test
    @DisplayName("TC-01: Ordinary Integer function reads positive, negative, and zero values")
    void testTC01_OrdinaryIntegerInput() {
      Scanner scanner = new Scanner("10 -25 0 42 -7");
      Integer[] array = new Integer[5];
      service.inputArray(array, scanner, null);
      assertArrayEquals(new Integer[] {10, -25, 0, 42, -7}, array);
    }

    @Test
    @DisplayName("TC-02: Template function with T = Integer matches ordinary behavior")
    void testTC02_TemplateIntegerInput() {
      Scanner scanner = new Scanner("10 -25 0 42 -7");
      Integer[] array = new Integer[5];
      service.inputArrayTemplate(array, scanner, Integer::parseInt, null);
      assertArrayEquals(new Integer[] {10, -25, 0, 42, -7}, array);
    }

    @Test
    @DisplayName("TC-03: Template function with T = Double reads floating-point numbers accurately")
    void testTC03_TemplateDoubleInput() {
      Scanner scanner = new Scanner("3.14 -0.005 0.0 100.25");
      Double[] array = new Double[4];
      service.inputArrayTemplate(array, scanner, Double::parseDouble, null);
      assertEquals(3.14, array[0], 1e-9);
      assertEquals(-0.005, array[1], 1e-9);
      assertEquals(0.0, array[2], 1e-9);
      assertEquals(100.25, array[3], 1e-9);
    }

    @Test
    @DisplayName("TC-04: Template function with T = String reads non-numeric token sequences")
    void testTC04_TemplateStringInput() {
      Scanner scanner = new Scanner("alpha beta gamma");
      String[] array = new String[3];
      service.inputArrayTemplate(array, scanner, s -> s, null);
      assertArrayEquals(new String[] {"alpha", "beta", "gamma"}, array);
    }

    @Test
    @DisplayName("TC-05: Single element vector (n = 1) populated correctly")
    void testTC05_SingleElement() {
      Scanner scanner = new Scanner("-999");
      Integer[] array = new Integer[1];
      service.inputArray(array, scanner, null);
      assertArrayEquals(new Integer[] {-999}, array);
    }

    @Test
    @DisplayName("TC-06: Degenerate vector (n = 0) performs zero stream extractions")
    void testTC06_ZeroElements() {
      Scanner scanner = new Scanner("100 200");
      Integer[] array = new Integer[0];
      service.inputArray(array, scanner, null);
      assertEquals("100", scanner.next());
    }

    @Test
    @DisplayName("TC-07: Resilient to whitespace, newlines, tabs, and retains trailing tokens")
    void testTC07_WhitespaceAndTrailingTokens() {
      Scanner scanner = new Scanner("  5 \n\t 12 \n  -8  99 100");
      Integer[] array = new Integer[3];
      service.inputArray(array, scanner, null);
      assertArrayEquals(new Integer[] {5, 12, -8}, array);
      assertEquals("99", scanner.next());
    }

    @Test
    @DisplayName("Format array produces uniform column widths")
    void testFormatArray() {
      Integer[] array = new Integer[] {1, 2, 3};
      String formatted = service.formatArrayTemplate(array, 4);
      assertEquals("   1   2   3", formatted);
    }
  }

  @Nested
  @DisplayName("Recursive Approach Tests")
  class RecursiveTests {

    private final RecursiveInputService service = new RecursiveInputService();

    @Test
    @DisplayName(
        "TC-01: Ordinary Integer function reads positive, negative, and zero values recursively")
    void testTC01_OrdinaryIntegerInput() {
      Scanner scanner = new Scanner("10 -25 0 42 -7");
      Integer[] array = new Integer[5];
      service.inputArray(array, 0, scanner, null);
      assertArrayEquals(new Integer[] {10, -25, 0, 42, -7}, array);
    }

    @Test
    @DisplayName("TC-02: Template function with T = Integer matches ordinary recursive behavior")
    void testTC02_TemplateIntegerInput() {
      Scanner scanner = new Scanner("10 -25 0 42 -7");
      Integer[] array = new Integer[5];
      service.inputArrayTemplate(array, 0, scanner, Integer::parseInt, null);
      assertArrayEquals(new Integer[] {10, -25, 0, 42, -7}, array);
    }

    @Test
    @DisplayName(
        "TC-03: Template function with T = Double reads floating-point numbers recursively")
    void testTC03_TemplateDoubleInput() {
      Scanner scanner = new Scanner("3.14 -0.005 0.0 100.25");
      Double[] array = new Double[4];
      service.inputArrayTemplate(array, 0, scanner, Double::parseDouble, null);
      assertEquals(3.14, array[0], 1e-9);
      assertEquals(-0.005, array[1], 1e-9);
      assertEquals(0.0, array[2], 1e-9);
      assertEquals(100.25, array[3], 1e-9);
    }

    @Test
    @DisplayName("TC-04: Template function with T = String reads string tokens recursively")
    void testTC04_TemplateStringInput() {
      Scanner scanner = new Scanner("alpha beta gamma");
      String[] array = new String[3];
      service.inputArrayTemplate(array, 0, scanner, s -> s, null);
      assertArrayEquals(new String[] {"alpha", "beta", "gamma"}, array);
    }

    @Test
    @DisplayName("TC-05: Single element vector (n = 1) populated recursively")
    void testTC05_SingleElement() {
      Scanner scanner = new Scanner("-999");
      Integer[] array = new Integer[1];
      service.inputArray(array, 0, scanner, null);
      assertArrayEquals(new Integer[] {-999}, array);
    }

    @Test
    @DisplayName("TC-06: Degenerate vector (n = 0) returns immediately without reading stream")
    void testTC06_ZeroElements() {
      Scanner scanner = new Scanner("100 200");
      Integer[] array = new Integer[0];
      service.inputArray(array, 0, scanner, null);
      assertEquals("100", scanner.next());
    }

    @Test
    @DisplayName(
        "TC-07: Resilient to diverse delimiters and leaves remaining stream intact recursively")
    void testTC07_WhitespaceAndTrailingTokens() {
      Scanner scanner = new Scanner("  5 \n\t 12 \n  -8  99 100");
      Integer[] array = new Integer[3];
      service.inputArray(array, 0, scanner, null);
      assertArrayEquals(new Integer[] {5, 12, -8}, array);
      assertEquals("99", scanner.next());
    }

    @Test
    @DisplayName("Format array produces uniform column widths recursively")
    void testFormatArray() {
      Integer[] array = new Integer[] {1, 2, 3};
      String formatted = service.formatArrayTemplate(array, 0, 4);
      assertEquals("   1   2   3", formatted);
    }
  }

  @Nested
  @DisplayName("Callback Compatibility Tests")
  class CallbackCompatibilityTests {

    @Test
    @DisplayName("fillIntArray and fillGenericArray work with reader supplier")
    void testCallbacks() {
      IterativeInputService itService = new IterativeInputService();
      String[] tokens = {"10", "20"};
      int[] idx = {0};
      Integer[] arr = new Integer[2];
      itService.fillIntArray(arr, () -> tokens[idx[0]++], null, "test");
      assertArrayEquals(new Integer[] {10, 20}, arr);
      assertEquals("[ 10, 20 ]", itService.formatArray(arr));
    }
  }
}
