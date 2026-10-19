package com.lpnu.alg_lb3.var.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PiecewiseExpressionTest {

  private final ShortFormExpression shortForm = new ShortFormExpression();
  private final FullFormExpression fullForm = new FullFormExpression();

  @Test
  void secondBranchEvaluatesQuadraticWhenBIsZero() {
    // For Variant 17: F = x^2 - cx
    BigDecimal a = BigDecimal.ONE;
    BigDecimal b = BigDecimal.ZERO;
    BigDecimal c = BigDecimal.valueOf(3);
    BigDecimal x = BigDecimal.valueOf(5);

    BigDecimal expected = BigDecimal.valueOf(10); // 5^2 - 3*5 = 25 - 15 = 10

    assertEquals(expected, shortForm.evaluate(a, b, c, x));
    assertEquals(expected, fullForm.evaluate(a, b, c, x));
  }

  @Test
  void thirdBranchEvaluatesDefaultCase() {
    BigDecimal a = BigDecimal.ONE;
    BigDecimal b = BigDecimal.valueOf(5);
    BigDecimal c = BigDecimal.valueOf(3);
    BigDecimal x = BigDecimal.valueOf(2);

    BigDecimal expected = BigDecimal.ONE; // -x / (a-c) = -2 / (1-3) = -2 / -2 = 1

    assertEquals(expected, shortForm.evaluate(a, b, c, x));
    assertEquals(expected, fullForm.evaluate(a, b, c, x));
  }
}
