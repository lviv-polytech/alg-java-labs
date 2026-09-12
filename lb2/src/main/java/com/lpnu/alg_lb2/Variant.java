package com.lpnu.alg_lb2;

import java.math.BigDecimal;

/** Defines a laboratory variant that computes a result from input parameters. */
public interface Variant {

  /**
   * Evaluates the variant expression for the provided input data.
   *
   * @param params the input values for the calculation
   * @return the computed result values
   */
  ExpressionResult expression(InParams params);

  /**
   * Stores the final values produced by a variant computation.
   *
   * @param z1 the first computed value
   * @param z2 the second computed value
   */
  record ExpressionResult(BigDecimal z1, BigDecimal z2) {}
}
