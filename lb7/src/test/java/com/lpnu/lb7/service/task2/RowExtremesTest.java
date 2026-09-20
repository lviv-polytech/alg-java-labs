package com.lpnu.lb7.service.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RowExtremesTest {

    private final IterativeRowExtremesService itService = new IterativeRowExtremesService();
    private final RecursiveRowExtremesService recService = new RecursiveRowExtremesService();

    @Test
    void testEquivalenceAndCorrectness() {
        int[][] original = {
                {40, 10, 50}, // index 0 (Math Odd). MIN is 10 at col_idx 1.
                {20, 90, 30}, // index 1 (Math Even). MAX is 90 at col_idx 1.
                {15, 60, 25}, // index 2 (Math Odd). MIN is 15 at col_idx 0.
                {80, 70, 75}  // index 3 (Math Even). MAX is 80 at col_idx 0.
        };

        // Expected outcome:
        // Pair 1: Swap 10 & 90. Row 0 becomes {40, 90, 50}. Row 1 becomes {20, 10, 30}.
        // Pair 2: Swap 15 & 80. Row 2 becomes {80, 60, 25}. Row 3 becomes {15, 70, 75}.

        int[][] itMatrix = deepCopy(original);
        int[][] recMatrix = deepCopy(original);

        itService.swapExtremes(itMatrix);
        recService.swapExtremes(recMatrix);

        int[][] expected = {
                {40, 90, 50},
                {20, 10, 30},
                {80, 60, 25},
                {15, 70, 75}
        };

        for (int i = 0; i < expected.length; i++) {
            Assertions.assertArrayEquals(expected[i], itMatrix[i], "Iterative mismatch at row " + i);
            Assertions.assertArrayEquals(expected[i], recMatrix[i], "Recursive mismatch at row " + i);
        }
    }

    private int[][] deepCopy(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].clone();
        }
        return copy;
    }
}