package com.lpnu.alg_lb1;

class FactorialCalculatorImpl implements FactorialCalculator {

  @Override
  public long calculateFactorial(int number) {
    if (number == 0) {
      return 1;
    }

    long factorial = 1;
    int i = 1;
    while (i <= number) {
      factorial = factorial * i;
      i++;
    }

    return factorial;
  }
}
