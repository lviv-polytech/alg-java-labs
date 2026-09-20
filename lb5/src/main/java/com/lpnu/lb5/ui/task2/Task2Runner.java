package com.lpnu.lb5.ui.task2;

import com.lpnu.common.SafeScanner;
import com.lpnu.common.math.TaylorResult;
import com.lpnu.common.math.Variant17Math;

public class Task2Runner {

    public void run() {
        System.out.println("--- Task 2: Taylor Series Recurrent Tabulation (Modular Functions) ---");

        SafeScanner scanner = new SafeScanner();
        
        double xStart;
        while (true) {
            xStart = promptDouble(scanner, "Enter xStart (must be > 0.5): ");
            if (xStart > 0.5) break;
            System.out.println("Error: xStart must be strictly greater than 0.5.");
        }
        
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

        printTableHeader();

        double x = xStart;
        while (x <= xEnd + 1e-9) {
            // Execution delegates to the strictly decoupled function in common-utils
            // The method returns a Record structure mimicking pass-by-reference output params.
            TaylorResult result = Variant17Math.computeLnTaylorSeries(x, eps);
            double referenceValue = Math.log(x);
            
            printTableRow(x, referenceValue, result.sum(), result.termsCount());
            x += dx;
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
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }
    }

    private void printTableHeader() {
        System.out.println("\n+------------+----------------+----------------+--------------+");
        System.out.printf("| %-10s | %-14s | %-14s | %-12s |%n", "x", "Math.log(x)", "Taylor Sum", "Terms (n)");
        System.out.println("+------------+----------------+----------------+--------------+");
    }

    private void printTableRow(double x, double mathVal, double taylorVal, int terms) {
        System.out.printf("| %-10.4f | %-14.6f | %-14.6f | %-12d |%n", x, mathVal, taylorVal, terms);
    }

    private void printTableFooter() {
        System.out.println("+------------+----------------+----------------+--------------+");
    }
}