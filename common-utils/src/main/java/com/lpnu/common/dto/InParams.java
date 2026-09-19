package com.lpnu.common.dto;

import java.math.BigDecimal;

/**
 * Input parameter pair used across lab variants.
 *
 * Keep this simple value carrier as a record for immutability.
 */
public record InParams(BigDecimal m, BigDecimal n) {}
