package com.lpnu.alg_lb2.var;

import static java.lang.System.out;

import com.lpnu.alg_lb2.Variant;
import com.lpnu.alg_lb2.dto.InParams;
import com.lpnu.alg_lb2.dto.Lb2ExpressionResult;
import java.math.BigDecimal;
import java.math.MathContext;

public class SevenTeen implements Variant<Lb2ExpressionResult> {

  @Override
  public Lb2ExpressionResult expression(InParams params) {

    MathContext mc = MathContext.DECIMAL128;
    var THREE = BigDecimal.valueOf(3);

    var BLOCK_1 = THREE.multiply(params.m()).add(BigDecimal.TWO).pow(2);
    out.println("Block 1=" + BLOCK_1);
    var BLOCK_2 = BigDecimal.valueOf(24).multiply(params.m());
    out.println("Block 2=" + BLOCK_2);

    var WRAPPER_1 = BLOCK_1.subtract(BLOCK_2).sqrt(mc);
    out.println("Wrapper 1=" + WRAPPER_1);

    var BLOCK_3 = params.m().sqrt(mc).multiply(THREE);
    out.println("Block 3=" + BLOCK_3);
    var BLOCK_4 = BigDecimal.TWO.divide(params.m().sqrt(mc), mc);
    out.println("Block 4=" + BLOCK_4);

    var WRAPPER_2 = BLOCK_3.subtract(BLOCK_4);
    out.println("Wrapper 2=" + WRAPPER_2);

    // expression z2
    var z2 = params.m().sqrt(mc);

    return new Lb2ExpressionResult(WRAPPER_1.divide(WRAPPER_2, mc), z2);
  }
}
