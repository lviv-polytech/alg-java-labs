package com.lpnu.lb5.service.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PiecewiseFunctionServiceTest {

    private final PiecewiseFunctionService service = new PiecewiseFunctionService();

    @Test
    void testBranch1_GreaterThan1() {
        // x = 2.0 -> |x| >= 1
        // h(2) = (sin(2) + 1) / (1 + cos(2))
        double expected = (Math.sin(2.0) + 1.0) / (1.0 + Math.cos(2.0));
        double actual = service.evaluateH(2.0);
        Assertions.assertEquals(expected, actual, 1e-9);
    }
    
    @Test
    void testBranch1_Boundary1() {
        // x = 1.0 -> |x| >= 1
        // h(1) = (sin(1) + 1) / (1 + cos(1))
        double expected = (Math.sin(1.0) + 1.0) / (1.0 + Math.cos(1.0));
        double actual = service.evaluateH(1.0);
        Assertions.assertEquals(expected, actual, 1e-9);
    }

    @Test
    void testBranch2_LessThan1() {
        // x = 0.5 -> |x| < 1
        // sum_{i=0}^5 0.5^i / (2i)!
        // i=0: 1
        // i=1: 0.5 / 2! = 0.5 / 2 = 0.25
        // i=2: 0.5^2 / 4! = 0.25 / 24 = 0.01041666...
        // i=3: 0.5^3 / 6! = 0.125 / 720 = 0.00017361...
        // i=4: 0.5^4 / 8! = 0.0625 / 40320 = 0.00000155...
        // i=5: 0.5^5 / 10! = 0.03125 / 3628800 = 0.0000000086...
        double expected = 1.0 + 0.25 + (0.25/24.0) + (0.125/720.0) + (0.0625/40320.0) + (0.03125/3628800.0);
        double actual = service.evaluateH(0.5);
        Assertions.assertEquals(expected, actual, 1e-6);
    }

    @Test
    void testBranch2_Zero() {
        // x = 0.0 -> |x| < 1
        // sum_{i=0}^5 0^i / (2i)! -> i=0 is 1. All other terms multiply by x=0. Result = 1.0
        double actual = service.evaluateH(0.0);
        Assertions.assertEquals(1.0, actual, 1e-9);
    }

    @Test
    void testEvaluateComposite() {
        // Test basic integration without hardcoding exact deep maths
        // Just verify it doesn't crash and returns a finite valid number.
        double g = 0.5;
        double actual = service.evaluateComposite(g);
        Assertions.assertFalse(Double.isNaN(actual));
        Assertions.assertTrue(Double.isFinite(actual));
    }
}