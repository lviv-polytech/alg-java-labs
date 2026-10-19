package com.lpnu.lb5.ui.task3;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.service.task3.PiecewiseFunctionService;
import java.util.Locale;

public class Task3Runner {

  private static final String BORDER = "+--------------+--------------------+";

  public void run() {
    System.out.println("--- Task 3: Piecewise Functions and Recurrent Loops ---");

    SafeScanner scanner = new SafeScanner();

    double gStart = promptDouble(scanner, "Enter start of interval gStart: ");

    double gEnd;
    while (true) {
      gEnd = promptDouble(scanner, "Enter end of interval gEnd (must be >= gStart): ");
      if (gEnd >= gStart) break;
      System.out.println("Error: End of interval cannot be less than the start.");
    }

    int nSegments;
    while (true) {
      System.out.print("Enter number of segments N (must be > 0): ");
      String line = scanner.nextLineOrNull();
      if (line == null) System.exit(0);
      try {
        nSegments = Integer.parseInt(line.trim());
        if (nSegments > 0) break;
        System.out.println("Error: N must be greater than 0.");
      } catch (NumberFormatException e) {
        System.out.println("Invalid format. Try again.");
      }
    }

    scanner.close();

    // Calculate step logic analytically
    double dg = (gEnd - gStart) / nSegments;

    System.out.println();
    System.out.println(BORDER);
    System.out.printf(Locale.US, "| %-33s |%n", "    Function Tabulation Table");
    System.out.println(BORDER);
    System.out.printf(Locale.US, "| %12s | %18s |%n", "g", "Q(g)");
    System.out.println(BORDER);

    PiecewiseFunctionService service = new PiecewiseFunctionService();

    // Loop using step counter to avoid double-precision accumulation drifting
    for (int i = 0; i <= nSegments; i++) {
      double g = gStart + i * dg;
      double result = service.evaluateComposite(g);
      String gCell = formatNumericCell(g, 12, 4);
      String resCell = Double.isFinite(result) ? formatNumericCell(result, 18, 6) : "undefined";

      System.out.printf(Locale.US, "| %12s | %18s |%n", gCell, resCell);
    }

    System.out.println(BORDER);
    System.out.println("Task 3 completed.\n");
  }

  private static String formatNumericCell(double value, int width, int precision) {
    if (!Double.isFinite(value)) {
      return "undefined";
    }
    String fixed = String.format(Locale.US, "%." + precision + "f", value);
    if (fixed.length() <= width) {
      return fixed;
    }
    int sciPrecision = Math.max(1, width - 7);
    return String.format(Locale.US, "%." + sciPrecision + "e", value);
  }

  private double promptDouble(SafeScanner scanner, String message) {
    while (true) {
      System.out.print(message);
      String line = scanner.nextLineOrNull();
      if (line == null) System.exit(0);
      try {
        double value = Double.parseDouble(line.trim());
        if (Double.isFinite(value)) {
          return value;
        }
        System.out.println("Value must be finite. Try again.");
      } catch (NumberFormatException e) {
        System.out.println("Invalid format. Try again.");
      }
    }
  }
}
