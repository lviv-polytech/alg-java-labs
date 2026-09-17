package com.lpnu.alg_lb3.var;

import com.lpnu.alg_lb2.dto.InParams;
import com.lpnu.alg_lb2.util.Variant;
import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import java.math.BigDecimal;

/** Main orchestrator for the LB3 variant. */
public class SevenTeen implements Variant<Lb3ExpressionResult> {

  private final SingleVariableExpression singleVariableExpression = new SingleVariableExpression();
  private final PiecewiseFunction piecewiseFunction = new PiecewiseFunction();
  private final ShortFormExpression shortBranchExpression = new ShortFormExpression();
  private final FullFormExpression fullBranchExpression = new FullFormExpression();

  @Override
  public Lb3ExpressionResult expression(InParams params) {
    return new Lb3ExpressionResult(singleVariableExpression.evaluate(params.m(), params.n()));
  }

  public Lb3ExpressionResult evaluateSingleVariable(BigDecimal m, BigDecimal n) {
    return new Lb3ExpressionResult(singleVariableExpression.evaluate(m, n));
  }

  public Lb3ExpressionResult evaluatePiecewise(
      BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    return new Lb3ExpressionResult(piecewiseFunction.evaluate(a, b, c, x));
  }

  public BigDecimal evaluatePiecewiseShort(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    return shortBranchExpression.evaluate(a, b, c, x);
  }

  public BigDecimal evaluatePiecewiseFull(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal x) {
    return fullBranchExpression.evaluate(a, b, c, x);
  }
}
