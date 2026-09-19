package com.lpnu.alg_lb3.var.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PiecewiseExpressionTest {

  private final ShortFormExpression shortForm = new ShortFormExpression();
  private final FullFormExpression fullForm = new FullFormExpression();

  @Test
  void firstBranchEvaluatesPolynomial() {
    BigDecimal a = BigDecimal.ONE;
    BigDecimal b = BigDecimal.valueOf(2);
    BigDecimal c = BigDecimal.valueOf(3);
    BigDecimal x = BigDecimal.valueOf(-15);

    BigDecimal expected = BigDecimal.valueOf(272);

    assertEquals(expected, shortForm.evaluate(a, b, c, x));
    assertEquals(expected, fullForm.evaluate(a, b, c, x));
  }

  @Test
  void secondBranchEvaluatesFractionWhenBIsZero() {
    BigDecimal a = BigDecimal.ONE;
    BigDecimal b = BigDecimal.ZERO;
    BigDecimal c = BigDecimal.valueOf(3);
    BigDecimal x = BigDecimal.valueOf(5);

    BigDecimal expected = BigDecimal.valueOf(2);

    assertEquals(expected, shortForm.evaluate(a, b, c, x));
    assertEquals(expected, fullForm.evaluate(a, b, c, x));
  }

  @Test
  void thirdBranchEvaluatesDefaultCase() {
    BigDecimal a = BigDecimal.ONE;
    BigDecimal b = BigDecimal.valueOf(5);
    BigDecimal c = BigDecimal.valueOf(3);
    BigDecimal x = BigDecimal.valueOf(2);

    BigDecimal expected = BigDecimal.ONE;

    assertEquals(expected, shortForm.evaluate(a, b, c, x));
    assertEquals(expected, fullForm.evaluate(a, b, c, x));
  }
}
