package com.lpnu.alg_lb2;

import com.lpnu.alg_lb2.dto.Lb2ExpressionResult;
import com.lpnu.common.contract.Variant;
import com.lpnu.common.dto.InParams;
import com.lpnu.common.io.InputLineFactory;
import com.lpnu.alg_lb2.var.SevenTeen;

/** Entry point for the laboratory assignment application. */
public final class Main {

  /**
   * Runs the selected variant calculation using console input.
   *
   * @param args command-line arguments (currently unused)
   */
  public static void main(String[] args) {
    try (var inputLine = InputLineFactory.console()) {
      Variant<Lb2ExpressionResult> variant = new SevenTeen();
      var params = inputLine.in("m=", "n=");
      var result = variant.expression(params);
      System.out.printf("=== Result ===%nz1=%s%nz2=%s%n", result.z1(), result.z2());
    }
  }
}
