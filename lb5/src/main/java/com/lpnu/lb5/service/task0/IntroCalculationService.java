package com.lpnu.lb5.service.task0;

/**
 * Service providing isolated introductory calculation logic for Task 5.0. Pure computation is
 * isolated from console I/O for standalone unit testing.
 */
public class IntroCalculationService {

  /**
   * Computes the introductory test formula: calculate(x, y) = (x^2 + y) / 2.0.
   *
   * @param x first parameter
   * @param y second parameter
   * @return computed value (x * x + y) / 2.0
   */
  public double calculate(double x, double y) {
    return (x * x + y) / 2.0;
  }
}
