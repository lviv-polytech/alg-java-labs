package com.lpnu.lb6.service.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaxEvenProcessorTest {

    private final IterativeMaxEvenService itService = new IterativeMaxEvenService();
    private final RecursiveMaxEvenService recService = new RecursiveMaxEvenService();

    @Test
    void testEquivalenceNormalArray() {
        int[] arr = { 1, 4, 3, 8, 5, 2, 8, 9 }; 
        // max even is 8. Index 3 is the first one, but if we compare with > (strict) 
        // it retains the first largest. Let's see: arr[6]=8 > arr[3]=8 is false. So index 3.
        
        int itRes = itService.findMaxEvenIndex(arr);
        int recRes = recService.findMaxEvenIndex(arr);
        
        Assertions.assertEquals(3, itRes);
        Assertions.assertEquals(3, recRes);
    }
    
    @Test
    void testAllOddArray() {
        int[] arr = { 1, 3, 5, 7, 9 }; 
        
        int itRes = itService.findMaxEvenIndex(arr);
        int recRes = recService.findMaxEvenIndex(arr);
        
        Assertions.assertEquals(-1, itRes);
        Assertions.assertEquals(-1, recRes);
    }

    @Test
    void testOnlyOneEven() {
        int[] arr = { 1, 3, 5, 2, 9 }; 
        
        int itRes = itService.findMaxEvenIndex(arr);
        int recRes = recService.findMaxEvenIndex(arr);
        
        Assertions.assertEquals(3, itRes);
        Assertions.assertEquals(3, recRes);
    }

    @Test
    void testNegativeNumbers() {
        int[] arr = { -10, -5, -2, -100 }; 
        // Max even here is -2 at index 2.
        
        int itRes = itService.findMaxEvenIndex(arr);
        int recRes = recService.findMaxEvenIndex(arr);
        
        Assertions.assertEquals(2, itRes);
        Assertions.assertEquals(2, recRes);
    }
}