package com.lpnu.common.contract;

import com.lpnu.common.dto.ExpressionResult;
import com.lpnu.common.dto.InParams;

/** Generic contract for a laboratory variant that returns a specific result implementation. */
public interface Variant<R extends ExpressionResult> {

  R expression(InParams params);
}
