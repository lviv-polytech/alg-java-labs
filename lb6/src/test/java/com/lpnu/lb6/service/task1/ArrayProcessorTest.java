package com.lpnu.lb6.service.task1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Lab 6 - Subtask 1: Array Selection, Counting, Summing and Zeroing (Variant 17)")
class ArrayProcessorTest {

  // 25-element reference test vector from mathematical specification (Variant 17)
  private static final int[] REFERENCE_ARRAY = {
    12, 10, 15, 20, 30, // Group 1: i=0..4, r[0]=12 is divisible by 6
    25, 10, 15, 20, 30, // Group 2: i=5..9, r[5]=25 is NOT divisible by 6 (preserved)
    42, 10, 15, 20, 30, // Group 3: i=10..14, r[10]=42 is divisible by 6
    19, 10, 15, 20, 30, // Group 4: i=15..19, r[15]=19 is NOT divisible by 6 (preserved)
    73, 10, 15, 20, 30 // Group 5: i=20..24, r[20]=73 is NOT divisible by 6 (preserved)
  };

  @Nested
  @DisplayName("Iterative Approach Tests")
  class IterativeTests {

    private final IterativeArrayService service = new IterativeArrayService();

    @Test
    @DisplayName("Calculates count, sum and zeros array matching exact reference values")
    void testReferenceArrayCalculations() {
      int[] testArray = Arrays.copyOf(REFERENCE_ARRAY, REFERENCE_ARRAY.length);

      int count = service.countElements(testArray);
      int sum = service.sumElements(testArray);

      assertEquals(22, count, "Reference count must be 22");
      assertEquals(429, sum, "Reference sum must be 429");

      service.zeroElements(testArray);

      assertEquals(25, testArray[5], "Index 5 must remain 25");
      assertEquals(19, testArray[15], "Index 15 must remain 19");
      assertEquals(73, testArray[20], "Index 20 must remain 73");

      for (int i = 0; i < testArray.length; i++) {
        if (i != 5 && i != 15 && i != 20) {
          assertEquals(0, testArray[i], "Element at index " + i + " must be replaced by 0");
        }
      }
    }

    @Test
    @DisplayName("processAndModify executes pipeline returning correct result")
    void testProcessAndModify() {
      int[] testArray = Arrays.copyOf(REFERENCE_ARRAY, REFERENCE_ARRAY.length);
      ArrayProcessResult result = service.processAndModify(testArray);

      assertEquals(22, result.count());
      assertEquals(429, result.sum());
      assertEquals(25, testArray[5]);
      assertEquals(19, testArray[15]);
      assertEquals(73, testArray[20]);
    }

    @Test
    @DisplayName("Throws IllegalArgumentException for invalid array size or null")
    void testBoundaryAndExceptions() {
      assertThrows(IllegalArgumentException.class, () -> service.countElements(null));
      assertThrows(IllegalArgumentException.class, () -> service.countElements(new int[10]));
      assertThrows(IllegalArgumentException.class, () -> service.sumElements(new int[0]));
      assertThrows(IllegalArgumentException.class, () -> service.zeroElements(new int[26]));
    }
  }

  @Nested
  @DisplayName("Recursive Approach Tests")
  class RecursiveTests {

    private final RecursiveArrayService service = new RecursiveArrayService();

    @Test
    @DisplayName(
        "Calculates count, sum and zeros array matching exact reference values recursively")
    void testReferenceArrayCalculationsRec() {
      int[] testArray = Arrays.copyOf(REFERENCE_ARRAY, REFERENCE_ARRAY.length);

      int count = service.countElements(testArray, 0);
      int sum = service.sumElements(testArray, 0);

      assertEquals(22, count, "Recursive count must be 22");
      assertEquals(429, sum, "Recursive sum must be 429");

      service.zeroElements(testArray, 0);

      assertEquals(25, testArray[5], "Index 5 must remain 25");
      assertEquals(19, testArray[15], "Index 15 must remain 19");
      assertEquals(73, testArray[20], "Index 20 must remain 73");

      for (int i = 0; i < testArray.length; i++) {
        if (i != 5 && i != 15 && i != 20) {
          assertEquals(0, testArray[i], "Element at index " + i + " must be replaced by 0");
        }
      }
    }

    @Test
    @DisplayName("processAndModify executes recursive pipeline returning correct result")
    void testProcessAndModifyRec() {
      int[] testArray = Arrays.copyOf(REFERENCE_ARRAY, REFERENCE_ARRAY.length);
      ArrayProcessResult result = service.processAndModify(testArray, 0);

      assertEquals(22, result.count());
      assertEquals(429, result.sum());
      assertEquals(25, testArray[5]);
      assertEquals(19, testArray[15]);
      assertEquals(73, testArray[20]);
    }

    @Test
    @DisplayName("Throws IllegalArgumentException for invalid array size or null recursively")
    void testBoundaryAndExceptionsRec() {
      assertThrows(IllegalArgumentException.class, () -> service.countElements(null, 0));
      assertThrows(IllegalArgumentException.class, () -> service.countElements(new int[10], 0));
      assertThrows(IllegalArgumentException.class, () -> service.sumElements(new int[0], 0));
      assertThrows(IllegalArgumentException.class, () -> service.zeroElements(new int[26], 0));
    }
  }

  @Nested
  @DisplayName("Cross-Method Equivalence Verification")
  class EquivalenceTests {

    @Test
    @DisplayName(
        "Iterative and recursive approaches produce identical counts, sums, and modified arrays")
    void testEquivalenceOnRandomArray() {
      IterativeArrayService itService = new IterativeArrayService();
      RecursiveArrayService recService = new RecursiveArrayService();

      int[] original = new int[25];
      itService.generate(original, 4, 73);

      int[] arrIterative = Arrays.copyOf(original, original.length);
      int[] arrRecursive = Arrays.copyOf(original, original.length);

      int itCount = itService.countElements(arrIterative);
      int recCount = recService.countElements(arrRecursive, 0);
      assertEquals(itCount, recCount);

      int itSum = itService.sumElements(arrIterative);
      int recSum = recService.sumElements(arrRecursive, 0);
      assertEquals(itSum, recSum);

      itService.zeroElements(arrIterative);
      recService.zeroElements(arrRecursive, 0);
      assertArrayEquals(arrIterative, arrRecursive);
    }
  }
}
