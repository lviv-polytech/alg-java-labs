package com.lpnu.alg_lb3;

import static java.lang.System.out;

import com.lpnu.common.dto.InParams;
import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import com.lpnu.alg_lb3.var.task2.SevenTeen;
import com.lpnu.alg_lb3.var.task3.GraphFunctionCalculator;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      scanner.useLocale(Locale.US);

      SevenTeen variant = new SevenTeen();

      out.println("Task 1: single-variable function");
      out.print("Task 1 | m = ");
      BigDecimal m = scanner.nextBigDecimal();
      out.print("Task 1 | n = ");
      BigDecimal n = scanner.nextBigDecimal();
      Lb3ExpressionResult singleVariableResult = variant.expression(new InParams(m, n));
      out.println("Task 1 | y = " + singleVariableResult.y());

      out.println();
      out.println("Task 2: piecewise function F(x)");
      out.print("Task 2 | a = ");
      BigDecimal a = scanner.nextBigDecimal();
      out.print("Task 2 | b = ");
      BigDecimal b = scanner.nextBigDecimal();
      out.print("Task 2 | c = ");
      BigDecimal c = scanner.nextBigDecimal();
      out.print("Task 2 | x = ");
      BigDecimal x = scanner.nextBigDecimal();

      BigDecimal shortResult = variant.evaluatePiecewiseShort(a, b, c, x);
      BigDecimal fullResult = variant.evaluatePiecewiseFull(a, b, c, x);
      Lb3ExpressionResult piecewiseResult = variant.evaluatePiecewise(a, b, c, x);

      out.println("Task 2 | F(x) [short form] = " + shortResult);
      out.println("Task 2 | F(x) [full form] = " + fullResult);
      out.println("Task 2 | F(x) [final] = " + piecewiseResult.y());
      out.println(
          "Task 2 | results match: " + shortResult.compareTo(fullResult) + " (0 means equal)");

      out.println();
      out.println("Task 3: graph-defined function y = f(x)");
      out.print("Task 3 | x = ");
      double task3X = scanner.nextDouble();
      out.print("Task 3 | R = ");
      double task3R = scanner.nextDouble();

      double task3Y = GraphFunctionCalculator.calculate(task3X, task3R);
      out.println("Task 3 | y = " + task3Y);
      out.println("Task 3 | input variables: x = " + task3X + ", R = " + task3R);
    }
  }
}
