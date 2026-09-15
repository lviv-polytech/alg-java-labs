package com.lpnu.alg_lb2.util;

import com.lpnu.alg_lb2.dto.ExpressionResult;
import com.lpnu.alg_lb2.dto.InParams;

/** Generic contract for a laboratory variant that returns a specific result implementation. */
public interface Variant<R extends ExpressionResult> {

  R expression(InParams params);
}
