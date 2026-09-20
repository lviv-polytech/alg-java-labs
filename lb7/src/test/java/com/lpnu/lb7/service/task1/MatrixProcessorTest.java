package com.lpnu.lb7.service.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatrixProcessorTest {

    private final IterativeMatrixService itService = new IterativeMatrixService();
    private final RecursiveMatrixService recService = new RecursiveMatrixService();

    @Test
    void testLogicAndEquivalence() {
        // Manually creating a controlled 8x5 matrix
        int[][] original = {
                {20, 30, 40, 50, 60}, // row 0: {0,1}=(20,30)
                {15, 10, 11, 12, 13}, // row 1: {0,1}=(15,10)
                {20, 20, 10, 50, 90}, // row 2: match row 3 on {0}, fail on {1}, will swap?
                {15, 10, 11, 5, 13},  // row 3: matches row 1 on columns {0,1}. Checks col {3}. 5 < 12. Must swap with row 1!
                {60, 60, 60, 60, 60}, // row 4
                {11, 22, 33, 44, 55}, // row 5
                {12, 13, 14, 15, 16}, // row 6
                {15, 12, 12, 12, 12}  // row 7
        };
        
        int[][] itMatrix = deepCopy(original);
        int[][] recMatrix = deepCopy(original);

        // Sorting Test
        itService.sort(itMatrix);
        recService.sort(recMatrix, recMatrix.length);

        // Both matrices must match each other
        for (int i = 0; i < 8; i++) {
            Assertions.assertArrayEquals(itMatrix[i], recMatrix[i], "Mismatch output post-sort at row " + i);
        }

        // Processing Test
        MatrixProcessResult itRes = itService.process(itMatrix);
        MatrixProcessResult recRes = recService.process(recMatrix, 0, 0, 0, 0);

        Assertions.assertEquals(itRes.count(), recRes.count());
        Assertions.assertEquals(itRes.sum(), recRes.sum());
        
        // Zeros check equality 
        for (int i = 0; i < 8; i++) {
            Assertions.assertArrayEquals(itMatrix[i], recMatrix[i], "Mismatch zero-ing output at row " + i);
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