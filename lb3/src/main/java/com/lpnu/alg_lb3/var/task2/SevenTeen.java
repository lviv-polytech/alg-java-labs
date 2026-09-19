package com.lpnu.alg_lb3.var.task2;

import com.lpnu.common.dto.InParams;
import com.lpnu.common.contract.Variant;
import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import com.lpnu.alg_lb3.var.task1.SingleVariableExpression;
import java.math.BigDecimal;

/**
 * Main orchestrator for the LB3 variant (Variant 17).
 *
 * This class acts as the public entrypoint for task-specific computations.
 * The {@code expression} method accepts the canonical input parameters and
 * returns a typed result wrapper. Implementation delegates to package-private
 * classes that contain the concrete algorithms for each sub-task (single
 * variable evaluation, piecewise function, and graph function).
 *
 * Design notes (per project standards):
 * - Contracts are kept small and expressive; callers should interact only via
 *   the public contract (this class) and not rely on internal implementation
 *   classes. Implementation classes live in the same module and are package-
 *   private to keep the module boundary clean.
 */
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
