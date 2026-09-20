package com.lpnu.lb7.service.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatrixProcessingTest {

    private final IterativeMatrixProcessingService itService = new IterativeMatrixProcessingService();
    private final RecursiveMatrixProcessingService recService = new RecursiveMatrixProcessingService();

    @Test
    void testFindFirstNonPositiveRow() {
        double[][] matrix = {
                { 1.0, -2.0, 3.0 },  // row 0: pos
                { 0.0, -5.0, -1.0 }, // row 1: no pos!
                { -1.0, 2.0, -3.0 }  // row 2: pos
        };
        
        Assertions.assertEquals(1, itService.findFirstNonPositiveRow(matrix));
        Assertions.assertEquals(1, recService.findFirstNonPositiveRow(matrix));
        
        // No such row
        double[][] matrix2 = {
                { 1.0, -2.0, 3.0 },
                { 1.0, -5.0, -1.0 }
        };
        Assertions.assertEquals(-1, itService.findFirstNonPositiveRow(matrix2));
        Assertions.assertEquals(-1, recService.findFirstNonPositiveRow(matrix2));
    }

    @Test
    void testBuildMaxDiagonal() {
        double[][] original = {
                { 5.0, 3.0, 9.0 },
                { 2.0, 1.0, 8.0 },
                { 7.0, 6.0, 4.0 }
        };
        // Top 3 elements are 9.0, 8.0, 7.0.
        // We expect diagonal to be 9.0, 8.0, 7.0 dynamically.
        // Let's copy to both services and check diagonal values only.
        
        double[][] itOut = deepCopy(original);
        double[][] recOut = deepCopy(original);
        
        itService.buildMaxDiagonal(itOut);
        recService.buildMaxDiagonal(recOut);
        
        Assertions.assertEquals(9.0, itOut[0][0], 1e-9);
        Assertions.assertEquals(8.0, itOut[1][1], 1e-9);
        Assertions.assertEquals(7.0, itOut[2][2], 1e-9);
        
        Assertions.assertEquals(9.0, recOut[0][0], 1e-9);
        Assertions.assertEquals(8.0, recOut[1][1], 1e-9);
        Assertions.assertEquals(7.0, recOut[2][2], 1e-9);
    }
    
    private double[][] deepCopy(double[][] source) {
        double[][] copy = new double[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].clone();
        }
        return copy;
    }
}