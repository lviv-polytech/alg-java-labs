package com.lpnu.alg_lb3;

import static java.lang.System.out;

import com.lpnu.alg_lb2.dto.InParams;
import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import com.lpnu.alg_lb3.var.SevenTeen;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      scanner.useLocale(Locale.US);

      SevenTeen variant = new SevenTeen();

      out.println("Expression 1: single-variable function");
      out.print("m = ");
      BigDecimal m = scanner.nextBigDecimal();
      out.print("n = ");
      BigDecimal n = scanner.nextBigDecimal();
      Lb3ExpressionResult singleVariableResult = variant.expression(new InParams(m, n));
      out.println("Single-variable result (y) = " + singleVariableResult.y());

      out.println("Expression 2: piecewise function F(x)");
      out.print("a = ");
      BigDecimal a = scanner.nextBigDecimal();
      out.print("b = ");
      BigDecimal b = scanner.nextBigDecimal();
      out.print("c = ");
      BigDecimal c = scanner.nextBigDecimal();
      out.print("x = ");
      BigDecimal x = scanner.nextBigDecimal();

      BigDecimal shortResult = variant.evaluatePiecewiseShort(a, b, c, x);
      BigDecimal fullResult = variant.evaluatePiecewiseFull(a, b, c, x);
      Lb3ExpressionResult piecewiseResult = variant.evaluatePiecewise(a, b, c, x);

      out.println("F(x) [short form] = " + shortResult);
      out.println("F(x) [full form] = " + fullResult);
      out.println("F(x) [final] = " + piecewiseResult.y());
      out.println("Results match: " + shortResult.compareTo(fullResult) + " (0 means equal)");
    }
  }
}
