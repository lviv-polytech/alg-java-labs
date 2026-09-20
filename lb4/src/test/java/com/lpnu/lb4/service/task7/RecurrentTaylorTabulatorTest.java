package com.lpnu.lb4.service.task7;

import com.lpnu.lb4.infra.task7.RecurrentTaylorTabulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RecurrentTaylorTabulatorTest {

    private final TaylorSeriesTabulator tabulator = new RecurrentTaylorTabulator();

    @Test
    void testTaylorSeriesConvergenceNearOne() {
        // x = 1 near the center of convergence
        // ln(1) = 0. Our formula: (1-1)/1 = 0.
        List<TaylorRow> rows = tabulator.tabulate(1.0, 1.0, 1.0, 0.0001);
        Assertions.assertEquals(1, rows.size());
        Assertions.assertEquals(0.0, rows.get(0).taylorLog(), 1e-6);
        Assertions.assertEquals(0.0, rows.get(0).mathLog(), 1e-6);
    }

    @Test
    void testTaylorSeriesConvergenceNormalValue() {
        // x = 2
        // ln(2) ~ 0.693147
        double eps = 1e-6;
        List<TaylorRow> rows = tabulator.tabulate(2.0, 2.0, 1.0, eps);
        TaylorRow row = rows.get(0);
        
        // Ensure Taylor approximates Math.log up to the precision
        Assertions.assertEquals(row.mathLog(), row.taylorLog(), eps * 10); // buffer logic
    }
    
    @Test
    void testMultipleTabulationSteps() {
        // x in [1.5, 3.0] step 0.5
        List<TaylorRow> rows = tabulator.tabulate(1.5, 3.0, 0.5, 1e-5);
        Assertions.assertEquals(4, rows.size()); // 1.5, 2.0, 2.5, 3.0
        
        for (TaylorRow row : rows) {
            Assertions.assertEquals(Math.log(row.x()), row.taylorLog(), 1e-4);
        }
    }

    @Test
    void testInvalidInputs() {
        // x <= 0.5 is invalid domain for this specific expansion logically constrained in prompt
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(0.5, 2.0, 1.0, 0.001);
        });
        
        // Bad step
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(1.0, 2.0, -1.0, 0.001);
        });
        
        // Bad epsilon
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tabulator.tabulate(1.0, 2.0, 1.0, -0.01);
        });
    }
}