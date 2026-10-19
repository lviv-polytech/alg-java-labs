package com.lpnu.lb4.service.task7;

import com.lpnu.lb4.infra.task7.RecurrentTaylorTabulator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecurrentTaylorTabulatorTest {

  private final TaylorSeriesTabulator tabulator = new RecurrentTaylorTabulator();

  @Test
  void testTaylorSeriesConvergenceNearOne() {
    // x = 1 near the center of convergence
    // ln(1) = 0. Our formula: (1-1)/1 = 0.
    List<TaylorRow> rows = tabulator.tabulate(1.0, 1.0, 1.0, 0.0001);
    Assertions.assertEquals(1, rows.size());
    Assertions.assertEquals(0.0, rows.get(0).taylorLog(), 1e-12);
    Assertions.assertEquals(0.0, rows.get(0).mathLog(), 1e-12);
    Assertions.assertEquals(1, rows.get(0).termsCount());
  }

  @Test
  void testTaylorSeriesConvergenceNormalValue() {
    // x = 2, eps = 1e-4 -> 10 terms, sum = 0.69306485615079365
    double eps = 1e-4;
    List<TaylorRow> rows = tabulator.tabulate(2.0, 2.0, 1.0, eps);
    TaylorRow row = rows.get(0);

    Assertions.assertEquals(10, row.termsCount());
    Assertions.assertEquals(0.69306485615079365, row.taylorLog(), 1e-12);
    Assertions.assertEquals(row.mathLog(), row.taylorLog(), eps);
  }

  @Test
  void testMultipleTabulationSteps() {
    // x in [0.6, 2.0] step 0.2 -> 8 points without floating-point drift
    List<TaylorRow> rows = tabulator.tabulate(0.6, 2.0, 0.2, 1e-4);
    Assertions.assertEquals(8, rows.size());
    Assertions.assertEquals(0.6, rows.get(0).x(), 1e-12);
    Assertions.assertEquals(1.0, rows.get(2).x(), 1e-12);
    Assertions.assertEquals(2.0, rows.get(7).x(), 1e-12);

    for (TaylorRow row : rows) {
      Assertions.assertEquals(Math.log(row.x()), row.taylorLog(), 1e-4);
    }
  }

  @Test
  void testInvalidInputs() {
    // x <= 0.5 is invalid domain for this specific expansion logically constrained in prompt
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          tabulator.tabulate(0.5, 2.0, 1.0, 0.001);
        });

    // Bad step
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          tabulator.tabulate(1.0, 2.0, -1.0, 0.001);
        });

    // Bad epsilon
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          tabulator.tabulate(1.0, 2.0, 1.0, -0.01);
        });
  }
}
