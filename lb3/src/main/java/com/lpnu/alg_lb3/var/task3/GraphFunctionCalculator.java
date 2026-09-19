package com.lpnu.alg_lb3.var.task3;

/** Evaluates a piecewise function that matches the provided graph specification. */
public final class GraphFunctionCalculator {

  private GraphFunctionCalculator() {
    // Utility class.
  }

  /**
   * Computes the value of the graph-defined function for the supplied x and radius R.
   *
   * @param x the input coordinate on the X-axis
   * @param r the radius used to define the arc and linear segments
   * @return the computed Y value for the function
   */
  public static double calculate(double x, double r) {
    if (x <= -1.0 - r) {
      // Branch 1: horizontal line y = 1
      return 1.0;
    } else if (x <= -1.0) {
      // Branch 2: lower semicircle y = -sqrt(R^2 - (x + 1)^2)
      return -Math.sqrt(Math.pow(r, 2) - Math.pow(x + 1.0, 2));
    } else if (x <= 2.0) {
      // Branch 3: horizontal line y = -R
      return -r;
    } else {
      // Branch 4: line y = R * (x - 4) / 2
      return (r * (x - 4.0)) / 2.0;
    }
  }
}
