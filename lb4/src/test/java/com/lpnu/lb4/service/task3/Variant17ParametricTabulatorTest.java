package com.lpnu.lb4.service.task3;

import com.lpnu.lb4.infra.task3.Variant17ParametricTabulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Variant17ParametricTabulatorTest {

    private final ParametricFunctionTabulator tabulator = new Variant17ParametricTabulator();

    @Test
    void testBranch1_xPlus10LessThanZero_bIsZero() {
        // x = -15, a = 2, b = 0, c = 5
        // x + 10 = -5 < 0
        // b = 0
        // Expected: a * x^3 - (x + b) = 2 * (-15)^3 - (-15 + 0) = 2 * (-3375) + 15 = -6750 + 15 = -6735
        List<TabulationPoint> result = tabulator.tabulate(-15.0, -15.0, 1.0, 2.0, 0.0, 5.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(-6735.0, result.get(0).y(), 1e-6);
    }

    @Test
    void testBranch2_xPlus10GreaterThanZero_bIsZero() {
        // x = 5, a = 2, b = 0, c = 10
        // x + 10 = 15 > 0
        // b = 0
        // Expected: (x - a) / (x - c) = (5 - 2) / (5 - 10) = 3 / -5 = -0.6
        List<TabulationPoint> result = tabulator.tabulate(5.0, 5.0, 1.0, 2.0, 0.0, 10.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(-0.6, result.get(0).y(), 1e-6);
    }

    @Test
    void testBranch3_Otherwise_bNotZero() {
        // x = -15, a = 2, b = 3, c = 5
        // x + 10 = -5 < 0, but b != 0
        // Expected: (x - c) / (a - c) = (-15 - 5) / (2 - 5) = -20 / -3 = 6.666666...
        List<TabulationPoint> result = tabulator.tabulate(-15.0, -15.0, 1.0, 2.0, 3.0, 5.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(-20.0 / -3.0, result.get(0).y(), 1e-6);
    }
    
    @Test
    void testBranch3_Otherwise_xPlus10EqualsZero_bIsZero() {
        // x = -10, a = 2, b = 0, c = 5
        // x + 10 = 0 (neither < 0 nor > 0)
        // b = 0
        // Expected: (x - c) / (a - c) = (-10 - 5) / (2 - 5) = -15 / -3 = 5.0
        List<TabulationPoint> result = tabulator.tabulate(-10.0, -10.0, 1.0, 2.0, 0.0, 5.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(5.0, result.get(0).y(), 1e-6);
    }

    @Test
    void testDivisionByZeroInBranch2() {
        // x = 10, a = 2, b = 0, c = 10
        // expected division by zero (Double.NaN)
        List<TabulationPoint> result = tabulator.tabulate(10.0, 10.0, 1.0, 2.0, 0.0, 10.0);
        Assertions.assertTrue(Double.isNaN(result.get(0).y()));
    }

    @Test
    void testDivisionByZeroInBranch3() {
        // x = 5, a = 4, b = 3, c = 4
        // expected division by zero (Double.NaN)
        List<TabulationPoint> result = tabulator.tabulate(5.0, 5.0, 1.0, 4.0, 3.0, 4.0);
        Assertions.assertTrue(Double.isNaN(result.get(0).y()));
    }

    @Test
    void testInvalidInputs() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(10.0, 0.0, 1.0, 1.0, 1.0, 1.0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(0.0, 10.0, -1.0, 1.0, 1.0, 1.0);
        });
    }
}