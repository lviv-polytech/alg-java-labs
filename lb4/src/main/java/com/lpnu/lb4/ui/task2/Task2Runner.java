package com.lpnu.lb4.ui.task2;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.infra.task2.PiecewiseFunctionTabulator;
import com.lpnu.lb4.service.task2.FunctionTabulator;
import com.lpnu.lb4.service.task2.TabulationPoint;

import java.util.List;

public class Task2Runner {

    public void run() {
        System.out.println("--- Task 2: Function Tabulation ---");

        SafeScanner scanner = new SafeScanner();
        
        double xStart = 0;
        double xEnd = 0;
        double dx = 0;

        while (true) {
            System.out.print("Enter xStart: ");
            String line = scanner.nextLineOrNull();
            if (line == null) return;
            try {
                xStart = Double.parseDouble(line.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }

        while (true) {
            System.out.print("Enter xEnd (must be >= xStart): ");
            String line = scanner.nextLineOrNull();
            if (line == null) return;
            try {
                xEnd = Double.parseDouble(line.trim());
                if (xEnd < xStart) {
                    System.out.println("xEnd must be >= xStart.");
                    continue; // ask again
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }

        while (true) {
            System.out.print("Enter dX (must be > 0): ");
            String line = scanner.nextLineOrNull();
            if (line == null) return;
            try {
                dx = Double.parseDouble(line.trim());
                if (dx <= 0) {
                    System.out.println("dX must be > 0.");
                    continue; // ask again
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }

        scanner.close();

        // Perform calculation securely via isolated contract
        FunctionTabulator tabulator = new PiecewiseFunctionTabulator();
        List<TabulationPoint> result = tabulator.tabulate(xStart, xEnd, dx);

        printTable(result);
    }

    private void printTable(List<TabulationPoint> points) {
        String template = "| %-10.4f | %-14.4f |%n";
        String border = "+------------+----------------+";
        
        System.out.println();
        System.out.println(border);
        System.out.printf("| %-10s | %-14s |%n", "x", "y(x)");
        System.out.println(border);
        
        for (TabulationPoint p : points) {
            System.out.printf(template, p.x(), p.y());
        }
        
        System.out.println(border);
        System.out.println();
    }
}