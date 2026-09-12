package com.lpnu.alg_lb2;

import com.lpnu.alg_lb2.var.SevenTeen;

/**
 * Entry point for the laboratory assignment application.
 */
public final class Main {

  /**
   * Runs the selected variant calculation using console input.
   *
   * @param args command-line arguments (currently unused)
   */
  public static void main(String[] args) {
    try (var inputLine = new InputLine()) {
      var variant = new SevenTeen();
      Variant.ExpressionResult result = variant.expression(inputLine.in());
      System.out.printf("=== Result ===%nz1=%s%nz2=%s%n", result.z1(), result.z2());
    }
  }
}
