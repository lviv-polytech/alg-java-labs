package com.lpnu.alg_lb2.dto;

import java.math.BigDecimal;

/** Public input contract used by laboratory variants and input readers. */
public record InParams(BigDecimal m, BigDecimal n) {

  public InParams(BigDecimal m) {
    this(m, null);
  }
}
