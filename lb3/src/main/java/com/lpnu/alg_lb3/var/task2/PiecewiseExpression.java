package com.lpnu.alg_lb3.var.task2;

import java.math.BigDecimal;
import java.math.MathContext;

/** Common implementation details for the piecewise function F(x). */
public class PiecewiseExpression {

  protected static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

  public BigDecimal evaluate(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    if (x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) < 0 && b.compareTo(BigDecimal.ZERO) != 0) {
      return calculateFirstCase(a, b, c, x);
    }
    if (x.add(BigDecimal.TEN).compareTo(BigDecimal.ZERO) > 0 && b.compareTo(BigDecimal.ZERO) == 0) {
      return calculateSecondCase(a, c, x);
    }
    return calculateThirdCase(a, c, x);
  }

  protected BigDecimal calculateFirstCase(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    // F = a*x^2 - c*x + b
    return a.multiply(x.pow(2, MATH_CONTEXT)).subtract(c.multiply(x, MATH_CONTEXT)).add(b);
  }

  protected BigDecimal calculateSecondCase(BigDecimal a, BigDecimal c, BigDecimal x) {
    // F = x^2 - c*x
    return x.pow(2, MATH_CONTEXT).subtract(c.multiply(x, MATH_CONTEXT));
  }

  protected BigDecimal calculateThirdCase(BigDecimal a, BigDecimal c, BigDecimal x) {
    // F = -x / (a - c)
    return divide(x.negate(), a.subtract(c, MATH_CONTEXT));
  }

  protected BigDecimal divide(BigDecimal numerator, BigDecimal denominator) {
    if (denominator.compareTo(BigDecimal.ZERO) == 0) {
      throw new ArithmeticException("Division by zero in the expression evaluation.");
    }
    return numerator.divide(denominator, MATH_CONTEXT);
  }
}
