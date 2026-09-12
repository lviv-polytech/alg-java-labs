package com.lpnu.alg_lb1;

import static java.lang.System.out;

class Main {

  public static void main(String[] args) {
    out.println("Hello, World!");
    FactorialCalculator calculator = new FactorialCalculatorImpl();
    int NUMBER = 10;
    long result = calculator.calculateFactorial(NUMBER);
    out.println("Factorial of %d is: ".formatted(NUMBER) + result);
  }
}
