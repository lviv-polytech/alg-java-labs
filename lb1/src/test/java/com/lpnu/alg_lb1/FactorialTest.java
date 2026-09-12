package com.lpnu.alg_lb1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FactorialTest {

  FactorialCalculator calculator = new FactorialCalculatorImpl();

  @Test
  void test_factorial_of_10() {
    int NUMBER = 10;
    long expected = 3628800;
    long result = calculator.calculateFactorial(NUMBER);
    assertEquals(expected, result);
  }
}
