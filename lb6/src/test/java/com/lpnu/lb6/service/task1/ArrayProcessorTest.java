package com.lpnu.lb6.service.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ArrayProcessorTest {

    @Test
    void testEquivalenceIterativeRecursive() {
        IterativeArrayService itService = new IterativeArrayService();
        RecursiveArrayService recService = new RecursiveArrayService();

        int[] originalArray = new int[25];
        itService.generate(originalArray, 4, 73);

        // Copy array for both to ensure identical starting state
        int[] arrIterative = Arrays.copyOf(originalArray, originalArray.length);
        int[] arrRecursive = Arrays.copyOf(originalArray, originalArray.length);

        ArrayProcessResult itResult = itService.processAndModify(arrIterative);
        ArrayProcessResult recResult = recService.processAndModify(arrRecursive, 0, 0, 0);

        Assertions.assertEquals(itResult.count(), recResult.count(), "Counts must match");
        Assertions.assertEquals(itResult.sum(), recResult.sum(), "Sums must match");
        Assertions.assertArrayEquals(arrIterative, arrRecursive, "Modified arrays must match");
    }
    
    @Test
    void testSpecificLogic() {
        IterativeArrayService itService = new IterativeArrayService();
        
        // Let's create an array
        // Index 0: 5 (i=0 -> 0%5==0. 5%6 != 0 -> Keeps)
        // Index 1: 10 (i=1 -> 1%5!=0 -> Matches condition -> Sets to 0)
        // Index 5: 12 (i=5 -> 5%5==0. 12%6 == 0 -> Matches condition -> Sets to 0)
        int[] arr = new int[]{5, 10, 5, 5, 5, 12, 5}; 
        
        ArrayProcessResult res = itService.processAndModify(arr);
        
        // matches:
        // i=1 (idx not mult of 5) -> +10
        // i=2 (idx not mult of 5) -> +5
        // i=3 (idx not mult of 5) -> +5
        // i=4 (idx not mult of 5) -> +5
        // i=5 (mult of 6) -> +12
        // i=6 (idx not mult of 5) -> +5
        // Total matched: 6 elements. Sum = 10+5+5+5+12+5 = 42
        
        Assertions.assertEquals(6, res.count());
        Assertions.assertEquals(42, res.sum());
        Assertions.assertEquals(0, arr[1]);
        Assertions.assertEquals(5, arr[0]); // unharmed
        Assertions.assertEquals(0, arr[5]);
    }
}