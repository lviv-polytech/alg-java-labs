package com.lpnu.alg_lb3.var.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GraphFunctionCalculatorTest {

  @Test
  void firstBranchReturnsOne() {
    assertEquals(1.0, GraphFunctionCalculator.calculate(-10.0, 2.0), 1e-9);
  }

  @Test
  void secondBranchUsesLowerArcFormula() {
    double x = -2.5;
    double r = 2.0;
    double expected = -Math.sqrt(Math.pow(r, 2) - Math.pow(x + 1.0, 2));

    assertEquals(expected, GraphFunctionCalculator.calculate(x, r), 1e-9);
  }

  @Test
  void thirdBranchReturnsNegativeRadius() {
    assertEquals(-3.0, GraphFunctionCalculator.calculate(0.0, 3.0), 1e-9);
  }

  @Test
  void fourthBranchUsesLinearEquation() {
    assertEquals(9.0, GraphFunctionCalculator.calculate(10.0, 3.0), 1e-9);
  }
}
