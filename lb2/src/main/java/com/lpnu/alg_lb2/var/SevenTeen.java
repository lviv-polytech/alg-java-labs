package com.lpnu.alg_lb2.var;

import com.lpnu.alg_lb2.Variant;
import com.lpnu.common.dto.InParams;
import com.lpnu.alg_lb2.dto.Lb2ExpressionResult;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SevenTeen implements Variant<Lb2ExpressionResult> {

  private static final Logger LOGGER = Logger.getLogger(SevenTeen.class.getName());

  @Override
  public Lb2ExpressionResult expression(InParams params) {

    MathContext mc = MathContext.DECIMAL128;
    BigDecimal three = BigDecimal.valueOf(3);

    LOGGER.info(() -> "Starting LB2 calculation for m=" + params.m());

    BigDecimal block1 = three.multiply(params.m()).add(BigDecimal.TWO).pow(2);
    LOGGER.log(Level.FINE, "Formula part 1: block1 = {0}", block1);

    BigDecimal block2 = BigDecimal.valueOf(24).multiply(params.m());
    LOGGER.log(Level.FINE, "Formula part 1: block2 = {0}", block2);

    BigDecimal wrapper1 = block1.subtract(block2).sqrt(mc);
    LOGGER.log(Level.INFO, "Derived z1 numerator wrapper: {0}", wrapper1);

    BigDecimal sqrtM = params.m().sqrt(mc);
    BigDecimal block3 = sqrtM.multiply(three);
    LOGGER.log(Level.FINE, "Formula part 2: block3 = {0}", block3);

    BigDecimal block4 = BigDecimal.TWO.divide(sqrtM, mc);
    LOGGER.log(Level.FINE, "Formula part 2: block4 = {0}", block4);

    BigDecimal wrapper2 = block3.subtract(block4);
    LOGGER.log(Level.INFO, "Derived z1 denominator wrapper: {0}", wrapper2);

    BigDecimal z2 = sqrtM;
    BigDecimal z1 = wrapper1.divide(wrapper2, mc);

    LOGGER.log(Level.INFO, "Final comparison values: z1={0}, z2={1}", new Object[] {z1, z2});

    return new Lb2ExpressionResult(z1, z2);
  }
}
