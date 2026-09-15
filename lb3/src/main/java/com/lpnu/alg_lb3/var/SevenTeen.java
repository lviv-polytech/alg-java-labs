package com.lpnu.alg_lb3.var;

import com.lpnu.alg_lb2.dto.InParams;
import com.lpnu.alg_lb2.util.Variant;
import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import java.math.BigDecimal;
import java.math.MathContext;

/** Example variant implementation for LB3 that returns a custom result record. */
public class SevenTeen implements Variant<Lb3ExpressionResult> {

  private static final BigDecimal FOUR = new BigDecimal("4");
  private static final BigDecimal SEVEN = new BigDecimal("7");
  private static final BigDecimal EIGHT_POINT_THREE = new BigDecimal("8.3");

  @Override
  public Lb3ExpressionResult expression(InParams params) {

    MathContext mc = MathContext.DECIMAL128;

    // Fetching the parameter m from DTO to use in place of x in the formula
    BigDecimal m = params.m();

    // The common base part of the equation: m^2 + 1
    BigDecimal baseBlock = m.pow(2, mc).add(BigDecimal.ONE, mc);

    BigDecimal piecewiseResult;

    // Branching logic based on the system conditions
    if (m.compareTo(FOUR) < 0) {
      piecewiseResult = calculateFirstCondition(m, mc);
    } else if (m.compareTo(SEVEN) < 0) {
      piecewiseResult = calculateSecondCondition(m, mc);
    } else {
      piecewiseResult = calculateThirdCondition(m, mc);
    }

    // Final value: baseBlock + piecewiseResult
    BigDecimal finalWrapper = baseBlock.add(piecewiseResult, mc);

    return new Lb3ExpressionResult(finalWrapper);
  }

  /** Calculates the value for the condition m < 4 Formula: 4m^7 - m^5 + m^3 - 2 */
  private BigDecimal calculateFirstCondition(BigDecimal m, MathContext mc) {
    BigDecimal block1 = FOUR.multiply(m.pow(7, mc), mc);
    BigDecimal block2 = m.pow(5, mc);
    BigDecimal block3 = m.pow(3, mc);

    return block1.subtract(block2, mc).add(block3, mc).subtract(BigDecimal.TWO, mc);
  }

  /** Calculates the value for the condition 4 <= m < 7 Formula: arctg((|m| + 1) / 2) + 8.3m */
  private BigDecimal calculateSecondCondition(BigDecimal m, MathContext mc) {
    BigDecimal block1 = m.abs(mc).add(BigDecimal.ONE, mc).divide(BigDecimal.TWO, mc);

    // BigDecimal lacks a built-in arctg function, using java.lang.Math
    double arctgValue = Math.atan(block1.doubleValue());
    BigDecimal block2 = BigDecimal.valueOf(arctgValue);

    BigDecimal block3 = EIGHT_POINT_THREE.multiply(m, mc);

    return block2.add(block3, mc);
  }

  /** Calculates the value for the condition m >= 7 Formula: ln|2m + e^(4m+1)| */
  private BigDecimal calculateThirdCondition(BigDecimal m, MathContext mc) {
    BigDecimal block1 = FOUR.multiply(m, mc).add(BigDecimal.ONE, mc);

    // BigDecimal lacks a built-in exponent function, using java.lang.Math
    double expValue = Math.exp(block1.doubleValue());
    BigDecimal block2 = BigDecimal.valueOf(expValue);

    BigDecimal block3 = BigDecimal.TWO.multiply(m, mc);

    BigDecimal block4 = block3.add(block2, mc).abs(mc);

    // BigDecimal lacks a built-in natural logarithm function, using java.lang.Math
    double lnValue = Math.log(block4.doubleValue());

    return BigDecimal.valueOf(lnValue);
  }
}
