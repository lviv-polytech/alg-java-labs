package com.lpnu.lb5.ui.task2;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.service.task2.TaylorSeriesService;
import com.lpnu.lb5.service.task2.TaylorTableRow;
import java.util.List;
import java.util.Locale;

public class Task2Runner {

  private static final String BORDER =
      "+--------------+--------------------+--------------------+--------------+";

  public void run() {
    System.out.println("--- Task 2: Taylor Series Recurrent Tabulation (Modular Functions) ---");

    SafeScanner scanner = new SafeScanner();

    double xStart = promptDouble(scanner, "Enter xStart (domain is x > 0.5): ");

    double xEnd;
    while (true) {
      xEnd = promptDouble(scanner, "Enter xEnd (must be >= xStart): ");
      if (xEnd >= xStart) break;
      System.out.println("Error: xEnd must be >= xStart.");
    }

    double dx;
    while (true) {
      dx = promptDouble(scanner, "Enter dX (must be > 0): ");
      if (dx > 0) break;
      System.out.println("Error: dX must be > 0.");
    }

    double eps;
    while (true) {
      eps = promptDouble(scanner, "Enter precision eps (e.g. 0.0001): ");
      if (eps > 0) break;
      System.out.println("Error: eps must be > 0.");
    }

    scanner.close();

    TaylorSeriesService service = new TaylorSeriesService();
    List<TaylorTableRow> rows = service.tabulate(xStart, xEnd, dx, eps);

    printTableHeader();
    for (TaylorTableRow row : rows) {
      printTableRow(row);
    }
    printTableFooter();
    System.out.println("Task 2 completed.\n");
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

  public static String formatTableHeader() {
    StringBuilder sb = new StringBuilder();
    sb.append(BORDER).append(System.lineSeparator());
    sb.append(
            String.format(
                Locale.US, "| %-69s |%n", "      Taylor Series Tabulation for ln(x) (Variant 17)"))
        .append(BORDER)
        .append(System.lineSeparator());
    sb.append(
            String.format(
                Locale.US,
                "| %12s | %18s | %18s | %12s |%n",
                "x",
                "ln(x) (Exact)",
                "Taylor Sum",
                "Terms"))
        .append(BORDER);
    return sb.toString();
  }

  public static String formatTableRow(TaylorTableRow row) {
    String xCell = formatNumericCell(row.x(), 12, 4);
    String exactCell = row.exactLn().map(v -> formatNumericCell(v, 18, 8)).orElse("undefined");
    if (row.seriesResult().isEmpty()) {
      return String.format(
          Locale.US, "| %12s | %18s | %18s | %12s |", xCell, exactCell, "outside domain", "-");
    }
    var result = row.seriesResult().orElseThrow();
    String sumCell = formatNumericCell(result.sum(), 18, 8);
    return String.format(
        Locale.US, "| %12s | %18s | %18s | %12d |", xCell, exactCell, sumCell, result.termsCount());
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

  private void printTableHeader() {
    System.out.println();
    System.out.println(formatTableHeader());
  }

  private void printTableRow(TaylorTableRow row) {
    System.out.println(formatTableRow(row));
  }

  private void printTableFooter() {
    System.out.println(BORDER);
  }
}
