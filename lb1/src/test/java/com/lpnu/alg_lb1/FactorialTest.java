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

  @Test
  void test_factorial_of_5() {
    int number = 5;
    long expected = 120;
    long result = calculator.calculateFactorial(number);
    assertEquals(expected, result);
  }

  @Test
  void test_factorial_of_zero_returns_one() {
    int number = 0;
    long expected = 1;
    long result = calculator.calculateFactorial(number);
    assertEquals(expected, result);
  }

  @Test
  void test_factorial_of_negative_number_returns_one() {
    int number = -5;
    long expected = 1;
    long result = calculator.calculateFactorial(number);
    assertEquals(expected, result);
  }
}
