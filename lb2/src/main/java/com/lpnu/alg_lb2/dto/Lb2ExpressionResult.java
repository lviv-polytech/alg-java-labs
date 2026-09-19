package com.lpnu.alg_lb2.dto;

import java.math.BigDecimal;

/** LB2-specific implementation of the general result contract. */
public record Lb2ExpressionResult(BigDecimal z1, BigDecimal z2) implements ExpressionResult {}
