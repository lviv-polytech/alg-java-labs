package com.lpnu.alg_lb3;

import static java.lang.System.out;

import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import com.lpnu.alg_lb3.var.task2.SevenTeen;
import com.lpnu.alg_lb3.var.task3.GraphFunctionCalculator;
import com.lpnu.alg_lb3.var.task4.PointRegionChecker;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class Main {
  private static final String ANSI_ESCAPE_REGEX =
      "\u001B\\[[\\x30-\\x3f]*[\\x20-\\x2f]*[\\x40-\\x7e]|\u001B.";

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      scanner.useLocale(Locale.US);

      SevenTeen variant = new SevenTeen();

      out.println("Task 1: single-variable function");
      BigDecimal task1X = readBigDecimal(scanner, "Task 1 | x = ");
      BigDecimal t1Short = variant.evaluateSingleVariableShort(task1X);
      BigDecimal t1Full = variant.evaluateSingleVariableFull(task1X);
      out.println("Task 1 | y [short form] = " + t1Short);
      out.println("Task 1 | y [full form] = " + t1Full);
      out.println("Task 1 | results match: " + t1Short.compareTo(t1Full) + " (0 means equal)");

      out.println();
      out.println("Task 2: piecewise function F(x)");
      BigDecimal a = readBigDecimal(scanner, "Task 2 | a = ");
      BigDecimal b = readBigDecimal(scanner, "Task 2 | b = ");
      BigDecimal c = readBigDecimal(scanner, "Task 2 | c = ");
      BigDecimal x = readBigDecimal(scanner, "Task 2 | x = ");

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
      double task3X = readDouble(scanner, "Task 3 | x = ");
      double task3R = readDouble(scanner, "Task 3 | R = ");

      double task3Y = GraphFunctionCalculator.calculate(task3X, task3R);
      out.println("Task 3 | y = " + task3Y);
      out.println("Task 3 | input variables: x = " + task3X + ", R = " + task3R);

      out.println();
      out.println("Task 4: point region checker");
      double task4X = readDouble(scanner, "Task 4 | x = ");
      double task4Y = readDouble(scanner, "Task 4 | y = ");
      double task4R = readDouble(scanner, "Task 4 | R = ");

      PointRegionChecker checker = PointRegionChecker.create();
      boolean isInside = checker.isInside(task4X, task4Y, task4R);
      out.println(
          "Task 4 | Point ("
              + task4X
              + ", "
              + task4Y
              + ") is "
              + (isInside ? "INSIDE" : "OUTSIDE")
              + " the shaded region (R = "
              + task4R
              + ")");
    }
  }

  private static BigDecimal readBigDecimal(Scanner scanner, String prompt) {
    while (true) {
      out.print(prompt);
      if (!scanner.hasNext()) {
        System.exit(0);
      }
      String token = scanner.next().replaceAll(ANSI_ESCAPE_REGEX, "").trim();
      if (token.isEmpty()) {
        continue;
      }
      try {
        return new BigDecimal(token.replace(',', '.'));
      } catch (NumberFormatException e) {
        out.println("Invalid number format. Please enter a valid number.");
      }
    }
  }

  private static double readDouble(Scanner scanner, String prompt) {
    while (true) {
      out.print(prompt);
      if (!scanner.hasNext()) {
        System.exit(0);
      }
      String token = scanner.next().replaceAll(ANSI_ESCAPE_REGEX, "").trim();
      if (token.isEmpty()) {
        continue;
      }
      try {
        return Double.parseDouble(token.replace(',', '.'));
      } catch (NumberFormatException e) {
        out.println("Invalid number format. Please enter a valid number.");
      }
    }
  }
}
