package com.lpnu.lb4.service.task4;

import com.lpnu.lb4.infra.task4.Variant17GraphTabulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Variant17GraphTabulatorTest {

    private final GraphFunctionTabulator tabulator = new Variant17GraphTabulator();

    @Test
    void testBranch1_LeftHorizontal() {
        // R = 2, so boundary is -1 - 2 = -3
        // x = -4 <= -3 => y = 1
        List<TabulationPoint> result = tabulator.tabulate(-4.0, -4.0, 1.0, 2.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1.0, result.get(0).y(), 1e-6);
    }

    @Test
    void testBranch2_CircleArc() {
        // R = 2, boundary (-3, -1]
        // x = -2 -> y = -sqrt(2^2 - (-2+1)^2) = -sqrt(4 - 1) = -sqrt(3)
        List<TabulationPoint> result = tabulator.tabulate(-2.0, -2.0, 1.0, 2.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(-Math.sqrt(3.0), result.get(0).y(), 1e-6);
    }

    @Test
    void testBranch3_BottomHorizontal() {
        // R = 2, boundary (-1, 2]
        // x = 1 => y = -R = -2
        List<TabulationPoint> result = tabulator.tabulate(1.0, 1.0, 1.0, 2.0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(-2.0, result.get(0).y(), 1e-6);
    }

    @Test
    void testBranch4_SlopedLine() {
        // R = 2, boundary (2, inf)
        // x = 4 => y = (2/2) * (4 - 4) = 0
        // x = 6 => y = (2/2) * (6 - 4) = 2
        List<TabulationPoint> result1 = tabulator.tabulate(4.0, 4.0, 1.0, 2.0);
        Assertions.assertEquals(0.0, result1.get(0).y(), 1e-6);
        
        List<TabulationPoint> result2 = tabulator.tabulate(6.0, 6.0, 1.0, 2.0);
        Assertions.assertEquals(2.0, result2.get(0).y(), 1e-6);
    }

    @Test
    void testBoundary_CircleArcToLine() {
        // x = -1 exactly. It falls in branch 2 (-1 - R < x <= -1)
        // R = 2 => y = -sqrt(4 - (-1+1)^2) = -2
        List<TabulationPoint> result = tabulator.tabulate(-1.0, -1.0, 1.0, 2.0);
        Assertions.assertEquals(-2.0, result.get(0).y(), 1e-6);
    }
    
    @Test
    void testInvalidInputs() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(10.0, 0.0, 1.0, 2.0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(0.0, 10.0, -1.0, 2.0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(0.0, 10.0, 1.0, -2.0); // negative radius
        });
    }
}