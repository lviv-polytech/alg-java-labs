package com.lpnu.alg_lb3.var.task2;

import java.math.BigDecimal;

/** Short conditional form of the piecewise function F(x). */
public class ShortFormExpression extends PiecewiseExpression {

  @Override
  public BigDecimal evaluate(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    return (x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) < 0
            && b.compareTo(BigDecimal.ZERO) != 0)
        ? calculateFirstCase(a, b, c, x)
        : (x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) > 0
                && b.compareTo(BigDecimal.ZERO) == 0)
            ? calculateSecondCase(a, c, x)
            : calculateThirdCase(a, c, x);
  }
}
