package com.lpnu.alg_lb3.var.task2;

import java.math.BigDecimal;

/** Short conditional form of the piecewise function F(x). */
public class ShortFormExpression extends PiecewiseExpression {

  @Override
  public BigDecimal evaluate(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    if (x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) < 0 && b.compareTo(BigDecimal.ZERO) != 0) {
      return calculateFirstCase(a, b, c, x);
    }
    if (x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) > 0 && b.compareTo(BigDecimal.ZERO) == 0) {
      return calculateSecondCase(a, c, x);
    }
    return calculateThirdCase(a, c, x);
  }
}
