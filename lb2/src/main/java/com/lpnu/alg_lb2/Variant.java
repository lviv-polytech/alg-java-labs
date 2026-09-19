package com.lpnu.alg_lb2;

import com.lpnu.common.dto.ExpressionResult;
import com.lpnu.common.dto.InParams;


/** Generic contract for a laboratory variant with a typed response implementation. */
public interface Variant<R extends ExpressionResult> extends com.lpnu.common.contract.Variant<R> {

  @Override
  R expression(InParams params);
}
