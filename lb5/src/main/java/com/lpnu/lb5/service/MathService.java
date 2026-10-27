package com.lpnu.lb5.service;

/** A simple mathematical service for demonstrating unit testing. */
public class MathService {

  /**
   * Computes the sum of two integers.
   *
   * @param a first term
   * @param b second term
   * @return sum
   */
  public int sum(int a, int b) {
    return a + b;
  }

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
