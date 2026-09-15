package com.lpnu.alg_lb3.var;

import com.lpnu.alg_lb2.dto.InParams;
import com.lpnu.alg_lb2.util.Variant;
import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Example variant implementation for LB3 that returns a custom result record. */
public class SevenTeen implements Variant<Lb3ExpressionResult> {

  private static final Logger LOGGER = Logger.getLogger(SevenTeen.class.getName());

  private static final BigDecimal FOUR = new BigDecimal("4");
  private static final BigDecimal SEVEN = new BigDecimal("7");
  private static final BigDecimal EIGHT_POINT_THREE = new BigDecimal("8.3");

  static {
    LOGGER.setLevel(Level.ALL);
    LOGGER.setUseParentHandlers(true);
    for (Handler handler : LOGGER.getHandlers()) {
      handler.setLevel(Level.ALL);
    }
  }

  @Override
  public Lb3ExpressionResult expression(InParams params) {

    MathContext mc = MathContext.DECIMAL128;

    BigDecimal m = params.m();
    LOGGER.info(
        () ->
            "Input parameter: m="
                + m
                + "; formula selection will be based on the branch condition.");

    BigDecimal baseBlock = m.pow(2, mc).add(BigDecimal.ONE, mc);
    LOGGER.log(Level.FINE, "Common base block: m^2 + 1 = {0}", baseBlock);

    BigDecimal piecewiseResult;

    if (m.compareTo(FOUR) < 0) {
      LOGGER.log(Level.INFO, "Branch selection: m < 4 -> formula 1: 4m^7 - m^5 + m^3 - 2");
      piecewiseResult = calculateFirstCondition(m, mc);
    } else if (m.compareTo(SEVEN) < 0) {
      LOGGER.log(
          Level.INFO, "Branch selection: 4 <= m < 7 -> formula 2: atan((|m| + 1) / 2) + 8.3m");
      piecewiseResult = calculateSecondCondition(m, mc);
    } else {
      LOGGER.log(Level.INFO, "Branch selection: m >= 7 -> formula 3: ln|2m + e^(4m + 1)|");
      piecewiseResult = calculateThirdCondition(m, mc);
    }

    BigDecimal finalWrapper = baseBlock.add(piecewiseResult, mc);
    LOGGER.log(
        Level.INFO,
        "Final result: baseBlock={0}; piecewiseResult={1}; total={2}",
        new Object[] {baseBlock, piecewiseResult, finalWrapper});

    return new Lb3ExpressionResult(finalWrapper);
  }

  /** Calculates the value for the condition m < 4 Formula: 4m^7 - m^5 + m^3 - 2 */
  private BigDecimal calculateFirstCondition(BigDecimal m, MathContext mc) {
    BigDecimal mPow7 = m.pow(7, mc);
    BigDecimal mPow5 = m.pow(5, mc);
    BigDecimal mPow3 = m.pow(3, mc);
    BigDecimal block1 = FOUR.multiply(mPow7, mc);
    BigDecimal block2 = mPow5;
    BigDecimal block3 = mPow3;

    BigDecimal result = block1.subtract(block2, mc).add(block3, mc).subtract(BigDecimal.TWO, mc);

    LOGGER.log(
        Level.FINE,
        "Formula 1 details: m^7={0}, m^5={1}, m^3={2}, block1={3}, result={4}",
        new Object[] {mPow7, block2, block3, block1, result});
    LOGGER.log(Level.FINER, "Formula 1 debug: raw expression = 4*m^7 - m^5 + m^3 - 2");

    return result;
  }

  /** Calculates the value for the condition 4 <= m < 7 Formula: arctg((|m| + 1) / 2) + 8.3m */
  private BigDecimal calculateSecondCondition(BigDecimal m, MathContext mc) {
    BigDecimal normalizedBlock = m.abs(mc).add(BigDecimal.ONE, mc).divide(BigDecimal.TWO, mc);
    double arctgValue = Math.atan(normalizedBlock.doubleValue());
    BigDecimal block2 = BigDecimal.valueOf(arctgValue);
    BigDecimal block3 = EIGHT_POINT_THREE.multiply(m, mc);

    BigDecimal result = block2.add(block3, mc);

    LOGGER.log(
        Level.FINE,
        "Formula 2 details: normalized input={0}, atan={1}, linear term={2}, result={3}",
        new Object[] {normalizedBlock, block2, block3, result});
    LOGGER.log(
        Level.FINER,
        "Formula 2 debug: arctan input resolved with double conversion for {0}",
        normalizedBlock);

    return result;
  }

  /** Calculates the value for the condition m >= 7 Formula: ln|2m + e^(4m+1)| */
  private BigDecimal calculateThirdCondition(BigDecimal m, MathContext mc) {
    BigDecimal exponentInput = FOUR.multiply(m, mc).add(BigDecimal.ONE, mc);
    double expValue = Math.exp(exponentInput.doubleValue());
    BigDecimal block2 = BigDecimal.valueOf(expValue);
    BigDecimal block3 = BigDecimal.TWO.multiply(m, mc);
    BigDecimal block4 = block3.add(block2, mc).abs(mc);
    double lnValue = Math.log(block4.doubleValue());

    LOGGER.log(
        Level.FINE,
        "Formula 3 details: exponent input={0}, exp={1}, inner sum={2}, log input={3}, result={4}",
        new Object[] {exponentInput, block2, block4, block4, BigDecimal.valueOf(lnValue)});
    LOGGER.log(
        Level.FINER,
        "Formula 3 debug: natural log computed from abs(2m + e^(4m + 1)) = {0}",
        block4);

    return BigDecimal.valueOf(lnValue);
  }
}
