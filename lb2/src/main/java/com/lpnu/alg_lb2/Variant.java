package com.lpnu.alg_lb2;

import com.lpnu.alg_lb2.dto.ExpressionResult;
import com.lpnu.alg_lb2.dto.InParams;

/** Generic contract for a laboratory variant with a typed response implementation. */
public interface Variant<R extends ExpressionResult> extends com.lpnu.alg_lb2.util.Variant<R> {

  @Override
  R expression(InParams params);
}
