package com.lpnu.lb6.service.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ArrayModifierTest {
    
    private final IterativeArrayModifier itService = new IterativeArrayModifier();
    private final RecursiveArrayModifier recService = new RecursiveArrayModifier();

    @Test
    void testEquivalence() {
        double[] original = { 2.5, -1.0, 0.0, 3.1, -4.2, 0.0, 5.0, 1.2 };
        
        double[] arrIt = Arrays.copyOf(original, original.length);
        double[] arrRec = Arrays.copyOf(original, original.length);
        
        // 1. Positives
        // Positives: 2.5, 3.1, 5.0, 1.2 -> count = 4
        Assertions.assertEquals(4, itService.countPositiveElements(arrIt));
        Assertions.assertEquals(4, recService.countPositiveElements(arrRec, 0));
        
        // 2. Sum after last zero
        // Last zero is at index 5. Elements after: 5.0, 1.2 -> sum = 6.2
        Assertions.assertEquals(6.2, itService.sumAfterLastZero(arrIt), 1e-9);
        Assertions.assertEquals(6.2, recService.sumAfterLastZero(arrRec), 1e-9);
        
        // 3. Stable restructure ((int)v <= 1)
        // Original: 2.5, -1.0, 0.0, 3.1, -4.2, 0.0, 5.0, 1.2
        // Match (<=1): -1.0, 0.0, -4.2, 0.0, 1.2 (for 1.2 int part is 1)
        // Others (>1): 2.5, 3.1, 5.0
        // Result expected: -1.0, 0.0, -4.2, 0.0, 1.2, 2.5, 3.1, 5.0
        
        itService.stableRestructure(arrIt);
        recService.stableRestructure(arrRec);
        
        double[] expected = { -1.0, 0.0, -4.2, 0.0, 1.2, 2.5, 3.1, 5.0 };
        Assertions.assertArrayEquals(expected, arrIt, 1e-9);
        Assertions.assertArrayEquals(expected, arrRec, 1e-9);
    }
}