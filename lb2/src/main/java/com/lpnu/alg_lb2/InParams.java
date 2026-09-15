package com.lpnu.alg_lb2;

import java.math.BigDecimal;

/**
 * Container for the numeric values used by a variant implementation.
 *
 * @param m the first numeric input value, or {@code null} when it is not provided
 * @param n the second numeric input value, or {@code null} when it is not provided
 */
public record InParams(BigDecimal m, BigDecimal n) {

  /**
   * Creates a record instance with only the first value supplied.
   *
   * @param m the first numeric input value
   */
  public InParams(BigDecimal m) {
    this(m, null);
  }
}
