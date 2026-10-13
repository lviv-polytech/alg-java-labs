package com.lpnu.alg_lb3.var.task2;

import java.math.BigDecimal;

/** Full conditional form of the piecewise function F(x). */
public class FullFormExpression extends PiecewiseExpression {

  @Override
  public BigDecimal evaluate(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    BigDecimal result;
    boolean cond1 =
        x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) < 0 && b.compareTo(BigDecimal.ZERO) != 0;
    boolean cond2 =
        x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) > 0 && b.compareTo(BigDecimal.ZERO) == 0;

    if (cond1) {
      result = calculateFirstCase(a, b, c, x);
    } else {
      if (cond2) {
        result = calculateSecondCase(a, c, x);
      } else {
        result = calculateThirdCase(a, c, x);
      }
    }
    return result;
  }
}
