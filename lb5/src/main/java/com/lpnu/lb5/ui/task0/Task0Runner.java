package com.lpnu.lb5.ui.task0;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.service.task0.IntroCalculationService;

public class Task0Runner {

  public void run() {
    System.out.println("--- Task 0: Introductory Calculation (Task 5.0) ---");
    SafeScanner scanner = new SafeScanner();

    double a = promptDouble(scanner, "a = ");
    double b = promptDouble(scanner, "b = ");

    IntroCalculationService service = new IntroCalculationService();
    double res = service.calculate(a, b);

    System.out.println("Result = " + res);
    System.out.println("Task 0 completed.\n");
  }

  private double promptDouble(SafeScanner scanner, String message) {
    while (true) {
      System.out.print(message);
      String line = scanner.nextLineOrNull();
      if (line == null) {
        System.out.println();
        System.exit(0);
      }
      try {
        return Double.parseDouble(line.trim());
      } catch (NumberFormatException e) {
        System.out.println("Invalid format. Try again.");
      }
    }
  }
}
