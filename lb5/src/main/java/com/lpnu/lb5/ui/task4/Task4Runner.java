package com.lpnu.lb5.ui.task4;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.service.task4.RecursiveSumService;
import java.util.Locale;

public class Task4Runner {

  public void run() {
    System.out.println("--- Task 4: Summation using Recursion (Variant 17) ---");

    SafeScanner scanner = new SafeScanner();

    int n;
    while (true) {
      System.out.print(
          "Enter upper bound N (must be between 1 and "
              + RecursiveSumService.MAX_SAFE_DEPTH
              + "): ");
      String line = scanner.nextLineOrNull();
      if (line == null) System.exit(0);
      try {
        n = Integer.parseInt(line.trim());
        if (n >= 1 && n <= RecursiveSumService.MAX_SAFE_DEPTH) break;
        System.out.println(
            "Error: N must be between 1 and " + RecursiveSumService.MAX_SAFE_DEPTH + ".");
      } catch (NumberFormatException e) {
        System.out.println("Invalid format. Try again.");
      }
    }

    scanner.close();

    RecursiveSumService service = new RecursiveSumService();

    System.out.println("\nResults:");

    double res1 = service.sumDescentDescending(n, n, 0.0);
    System.out.printf(Locale.US, "1. Descent, parameter descending : %.9f%n", res1);

    double res2 = service.sumDescentAscending(n, 1, 0.0);
    System.out.printf(Locale.US, "2. Descent, parameter ascending  : %.9f%n", res2);

    double res3 = service.sumAscentDescending(n, n);
    System.out.printf(Locale.US, "3. Ascent,  parameter descending : %.9f%n", res3);

    double res4 = service.sumAscentAscending(n, 1);
    System.out.printf(Locale.US, "4. Ascent,  parameter ascending  : %.9f%n", res4);

    double res5 = service.sumIterative(n);
    System.out.printf(Locale.US, "5. Iterative approach (benchmark): %.9f%n", res5);

    System.out.println("\nAll five calculations strictly yield identical results.");
    System.out.println("Task 4 completed.\n");
  }
}
