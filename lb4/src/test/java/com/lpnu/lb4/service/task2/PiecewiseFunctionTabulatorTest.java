package com.lpnu.lb4.service.task2;

import com.lpnu.lb4.infra.task2.PiecewiseFunctionTabulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PiecewiseFunctionTabulatorTest {

    private final FunctionTabulator tabulator = new PiecewiseFunctionTabulator();

    @Test
    void testTabulateValidInterval() {
        List<TabulationPoint> points = tabulator.tabulate(0.0, 10.0, 1.0);
        
        // From 0 to 10 inclusive with step 1 means 11 points
        Assertions.assertEquals(11, points.size());

        // Check specific branch: x < 4 (e.g. x = 2)
        // y(2) = 2^2 + 1 + (4*(2^2) - 2^2 + 2^2 - 2) = 4 + 1 + 16 - 4 + 4 - 2 = 19
        Assertions.assertEquals(19.0, getPointByX(points, 2.0).y(), 1e-6);

        // Check specific branch: 4 <= x < 7 (e.g. x = 5)
        // y(5) = 5^2 + 1 + sin(2*5 + 1) = 26 + sin(11)
        double expectedY5 = 26.0 + Math.sin(11);
        Assertions.assertEquals(expectedY5, getPointByX(points, 5.0).y(), 1e-6);

        // Check specific branch: x >= 7 (e.g. x = 8)
        // y(8) = 8^2 + 1 + ln(2*8 + e^9) = 65 + ln(16 + e^9)
        double expectedY8 = 65.0 + Math.log(16.0 + Math.exp(9));
        Assertions.assertEquals(expectedY8, getPointByX(points, 8.0).y(), 1e-6);
    }

    @Test
    void testBoundaryPoints() {
        // Boundaries are exactly 4.0 and 7.0
        List<TabulationPoint> points = tabulator.tabulate(3.5, 7.5, 0.5);

        // x = 4.0 should use branch: 4 <= x < 7
        // y(4.0) = 4^2 + 1 + sin(8 + 1) = 17 + sin(9)
        double expectedY4 = 17.0 + Math.sin(9);
        Assertions.assertEquals(expectedY4, getPointByX(points, 4.0).y(), 1e-6);

        // x = 7.0 should use branch: x >= 7
        // y(7.0) = 7^2 + 1 + ln(14 + e^8) = 50 + ln(14 + e^8)
        double expectedY7 = 50.0 + Math.log(14.0 + Math.exp(8));
        Assertions.assertEquals(expectedY7, getPointByX(points, 7.0).y(), 1e-6);
    }

    @Test
    void testInvalidInputs() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(10.0, 0.0, 1.0); // xStart > xEnd
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(0.0, 10.0, -1.0); // dx < 0
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(0.0, 10.0, 0.0); // dx == 0
        });
    }

    private TabulationPoint getPointByX(List<TabulationPoint> points, double x) {
        return points.stream()
                .filter(p -> Math.abs(p.x() - x) < 1e-9)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Point x=" + x + " not found"));
    }
}