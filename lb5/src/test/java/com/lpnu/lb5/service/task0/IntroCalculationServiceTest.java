package com.lpnu.lb5.service.task0;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Task 5.0: Introductory Calculation Service Unit Tests")
class IntroCalculationServiceTest {

  private final IntroCalculationService service = new IntroCalculationService();

  @Test
  @DisplayName("Calculates reference test case: x=2.0, y=6.0 -> (2^2 + 6) / 2 = 5.0")
  void testCalculateReferenceValues() {
    // 1. Вхідні параметри
    double testX = 2.0;
    double testY = 6.0;

    // 2. Очікуваний результат: (2^2 + 6) / 2 = 5.0
    double expected = 5.0;

    // 3. Фактичний виклик піддослідної функції
    double actual = service.calculate(testX, testY);

    // 4. Твердження рівності з допустимою похибкою
    assertEquals(expected, actual, 0.00001);
  }

  @ParameterizedTest(name = "calculate({0}, {1}) = {2}")
  @CsvSource({
    "0.0, 0.0, 0.0",
    "-2.0, 6.0, 5.0",
    "3.0, 1.0, 5.0",
    "1.5, 2.5, 2.375",
    "-3.0, -1.0, 4.0"
  })
  @DisplayName("Calculates various input pairs correctly")
  void testCalculateVariousInputs(double x, double y, double expected) {
    assertEquals(expected, service.calculate(x, y), 0.00001);
  }
}
