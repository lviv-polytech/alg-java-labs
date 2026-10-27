package com.lpnu.lb5.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathServiceTest {

  @Test
  void testSum() {
    // Arrange
    MathService mathService = new MathService();
    int expectedResult = 15;

    // Act
    int actualResult = mathService.sum(10, 5);

    // Assert
    Assertions.assertEquals(expectedResult, actualResult, "The sum of 10 and 5 should be 15.");
  }

  @Test
  void testCalculate() {
    MathService mathService = new MathService();
    double expected = 5.0;
    double actual = mathService.calculate(2.0, 6.0);
    Assertions.assertEquals(expected, actual, 0.00001);
  }
}
