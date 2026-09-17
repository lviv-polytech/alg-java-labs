package com.lpnu.alg_lb3.var;

import java.math.BigDecimal;
import java.math.MathContext;

/** Original LB3 expression extracted from the previous SevenTeen implementation. */
public class SingleVariableExpression {

  private static final BigDecimal FOUR = new BigDecimal("4");
  private static final BigDecimal SEVEN = new BigDecimal("7");
  private static final BigDecimal EIGHT_POINT_THREE = new BigDecimal("8.3");
  private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

  public BigDecimal evaluate(BigDecimal m, BigDecimal n) {
    BigDecimal baseBlock = m.pow(2, MATH_CONTEXT).add(BigDecimal.ONE, MATH_CONTEXT);

    BigDecimal piecewiseResult;
    if (m.compareTo(FOUR) < 0) {
      piecewiseResult = calculateFirstCondition(m);
    } else if (m.compareTo(SEVEN) < 0) {
      piecewiseResult = calculateSecondCondition(m);
    } else {
      piecewiseResult = calculateThirdCondition(m);
    }

    return baseBlock.add(piecewiseResult, MATH_CONTEXT);
  }

  private BigDecimal calculateFirstCondition(BigDecimal m) {
    BigDecimal mPow7 = m.pow(7, MATH_CONTEXT);
    BigDecimal mPow5 = m.pow(5, MATH_CONTEXT);
    BigDecimal mPow3 = m.pow(3, MATH_CONTEXT);

    return FOUR.multiply(mPow7, MATH_CONTEXT)
        .subtract(mPow5, MATH_CONTEXT)
        .add(mPow3, MATH_CONTEXT)
        .subtract(BigDecimal.TWO, MATH_CONTEXT);
  }

  private BigDecimal calculateSecondCondition(BigDecimal m) {
    BigDecimal normalizedBlock =
        m.abs(MATH_CONTEXT).add(BigDecimal.ONE, MATH_CONTEXT).divide(BigDecimal.TWO, MATH_CONTEXT);
    double arctgValue = Math.atan(normalizedBlock.doubleValue());
    BigDecimal block2 = BigDecimal.valueOf(arctgValue);
    BigDecimal block3 = EIGHT_POINT_THREE.multiply(m, MATH_CONTEXT);
    return block2.add(block3, MATH_CONTEXT);
  }

  private BigDecimal calculateThirdCondition(BigDecimal m) {
    BigDecimal exponentInput = FOUR.multiply(m, MATH_CONTEXT).add(BigDecimal.ONE, MATH_CONTEXT);
    double expValue = Math.exp(exponentInput.doubleValue());
    BigDecimal block2 = BigDecimal.valueOf(expValue);
    BigDecimal block3 = BigDecimal.TWO.multiply(m, MATH_CONTEXT);
    BigDecimal block4 = block3.add(block2, MATH_CONTEXT).abs(MATH_CONTEXT);
    double lnValue = Math.log(block4.doubleValue());
    return BigDecimal.valueOf(lnValue);
  }
}
