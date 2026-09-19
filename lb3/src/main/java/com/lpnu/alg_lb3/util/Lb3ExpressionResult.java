package com.lpnu.alg_lb3.util;

import com.lpnu.common.dto.ExpressionResult;
import java.math.BigDecimal;

/** LB3-specific implementation of the common result contract. */
public record Lb3ExpressionResult(BigDecimal y) implements ExpressionResult {}
