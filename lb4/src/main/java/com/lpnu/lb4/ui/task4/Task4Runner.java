package com.lpnu.lb4.ui.task4;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.infra.task4.Variant17GraphTabulator;
import com.lpnu.lb4.service.task4.GraphFunctionTabulator;
import com.lpnu.lb4.service.task4.TabulationPoint;

import java.util.List;

public class Task4Runner {

    public void run() {
        System.out.println("--- Task 4: Graphic Function Tabulation ---");

        SafeScanner scanner = new SafeScanner();
        
        double r;
        while (true) {
            r = promptDouble(scanner, "Enter radius R (must be >= 0): ");
            if (r >= 0) break;
            System.out.println("Error: Radius cannot be negative.");
        }
        
        double xStart = promptDouble(scanner, "Enter xStart: ");
        
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

        scanner.close();

        GraphFunctionTabulator tabulator = new Variant17GraphTabulator();
        List<TabulationPoint> result = tabulator.tabulate(xStart, xEnd, dx, r);

        printTable(result);
    }
    
    private double promptDouble(SafeScanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLineOrNull();
            if (line == null) System.exit(0);
            try {
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }
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