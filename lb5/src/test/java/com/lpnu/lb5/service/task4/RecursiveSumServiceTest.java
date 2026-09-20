package com.lpnu.lb5.service.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecursiveSumServiceTest {

    private final RecursiveSumService service = new RecursiveSumService();

    @Test
    void testEquivalenceN5() {
        int n = 5;
        double expected = service.sumIterative(n);
        
        Assertions.assertEquals(expected, service.sumDescentDescending(n, n, 0.0), 1e-9);
        Assertions.assertEquals(expected, service.sumDescentAscending(n, 2, 0.0), 1e-9);
        Assertions.assertEquals(expected, service.sumAscentDescending(n, n), 1e-9);
        Assertions.assertEquals(expected, service.sumAscentAscending(n, 2), 1e-9);
    }
    
    @Test
    void testEquivalenceN10() {
        int n = 10;
        double expected = service.sumIterative(n);
        
        Assertions.assertEquals(expected, service.sumDescentDescending(n, n, 0.0), 1e-9);
        Assertions.assertEquals(expected, service.sumDescentAscending(n, 2, 0.0), 1e-9);
        Assertions.assertEquals(expected, service.sumAscentDescending(n, n), 1e-9);
        Assertions.assertEquals(expected, service.sumAscentAscending(n, 2), 1e-9);
    }

    @Test
    void testEmptyIntervalN1() {
        // Interval is j from 2 to N. If N=1, sum is 0.
        int n = 1;
        double expected = service.sumIterative(n); // Should be 0.0
        Assertions.assertEquals(0.0, expected, 1e-9);
        
        Assertions.assertEquals(expected, service.sumDescentDescending(n, n, 0.0), 1e-9);
        Assertions.assertEquals(expected, service.sumDescentAscending(n, 2, 0.0), 1e-9);
        Assertions.assertEquals(expected, service.sumAscentDescending(n, n), 1e-9);
        Assertions.assertEquals(expected, service.sumAscentAscending(n, 2), 1e-9);
    }
}