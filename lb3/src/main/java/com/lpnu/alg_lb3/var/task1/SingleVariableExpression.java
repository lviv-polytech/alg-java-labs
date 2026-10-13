package com.lpnu.alg_lb3.var.task1;

import java.math.BigDecimal;
import java.math.MathContext;

/** Original LB3 expression extracted from the previous SevenTeen implementation. */
public class SingleVariableExpression {

  private static final BigDecimal FOUR = new BigDecimal("4");
  private static final BigDecimal SEVEN = new BigDecimal("7");
  private static final BigDecimal EIGHT_POINT_THREE = new BigDecimal("8.3");
  private static final BigDecimal FIVE = new BigDecimal("5");
  private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

  public BigDecimal evaluateShort(BigDecimal x) {
    BigDecimal baseBlock = x.pow(2, MATH_CONTEXT).add(BigDecimal.ONE, MATH_CONTEXT);
    BigDecimal piecewiseResult = BigDecimal.ZERO;

    if (x.compareTo(FOUR) < 0) {
      piecewiseResult = calculateFirstCondition(x);
    }
    if (x.compareTo(FOUR) >= 0 && x.compareTo(SEVEN) < 0) {
      piecewiseResult = calculateSecondCondition(x);
    }
    if (x.compareTo(SEVEN) >= 0) {
      piecewiseResult = calculateThirdCondition(x);
    }

    return baseBlock.add(piecewiseResult, MATH_CONTEXT);
  }

  public BigDecimal evaluateFull(BigDecimal x) {
    BigDecimal baseBlock = x.pow(2, MATH_CONTEXT).add(BigDecimal.ONE, MATH_CONTEXT);

    BigDecimal piecewiseResult;

    if (x.compareTo(FOUR) < 0) {
      piecewiseResult = calculateFirstCondition(x);
    } else {
      if (x.compareTo(SEVEN) < 0) {
        piecewiseResult = calculateSecondCondition(x);
      } else {
        piecewiseResult = calculateThirdCondition(x);
      }
    }

    return baseBlock.add(piecewiseResult, MATH_CONTEXT);
  }

  private BigDecimal calculateFirstCondition(BigDecimal x) {
    // B = 5x^3 - x^2 - 2
    BigDecimal xPow3 = x.pow(3, MATH_CONTEXT);
    BigDecimal xPow2 = x.pow(2, MATH_CONTEXT);
    return FIVE.multiply(xPow3, MATH_CONTEXT)
        .subtract(xPow2, MATH_CONTEXT)
        .subtract(BigDecimal.TWO, MATH_CONTEXT);
  }

  private BigDecimal calculateSecondCondition(BigDecimal x) {
    // B = arctg((|x| + 1) / 2) + 8.3x
    BigDecimal normalizedBlock =
        x.abs(MATH_CONTEXT).add(BigDecimal.ONE, MATH_CONTEXT).divide(BigDecimal.TWO, MATH_CONTEXT);
    double arctgValue = Math.atan(normalizedBlock.doubleValue());
    return BigDecimal.valueOf(arctgValue)
        .add(EIGHT_POINT_THREE.multiply(x, MATH_CONTEXT), MATH_CONTEXT);
  }

  private BigDecimal calculateThirdCondition(BigDecimal x) {
    // B = ln|2x + e^(4+x)|
    double innerExp = Math.exp(4.0 + x.doubleValue());
    double innerValue = Math.abs(2.0 * x.doubleValue() + innerExp);
    return BigDecimal.valueOf(Math.log(innerValue));
  }
}
