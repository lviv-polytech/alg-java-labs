package com.lpnu.alg_lb3.var.task2;

import java.math.BigDecimal;

/** Combines the short and full branch implementations for the piecewise function. */
public class PiecewiseFunction {

  private final ShortFormExpression shortBranchExpression = new ShortFormExpression();
  private final FullFormExpression fullBranchExpression = new FullFormExpression();

  public BigDecimal evaluate(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    BigDecimal shortValue = shortBranchExpression.evaluate(a, b, c, x);
    BigDecimal fullValue = fullBranchExpression.evaluate(a, b, c, x);

    if (shortValue.compareTo(fullValue) != 0) {
      throw new IllegalStateException(
          "The short and full forms of the function differ: " + shortValue + " != " + fullValue);
    }

    return shortValue;
  }
}
