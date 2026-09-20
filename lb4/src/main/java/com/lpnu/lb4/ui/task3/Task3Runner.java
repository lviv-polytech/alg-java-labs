package com.lpnu.lb4.ui.task3;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.infra.task3.Variant17ParametricTabulator;
import com.lpnu.lb4.service.task3.ParametricFunctionTabulator;
import com.lpnu.lb4.service.task3.TabulationPoint;

import java.util.List;

public class Task3Runner {

    public void run() {
        System.out.println("--- Task 3: Parametric Function Tabulation ---");

        SafeScanner scanner = new SafeScanner();
        
        double a = promptDouble(scanner, "Enter parameter a: ");
        double b = promptDouble(scanner, "Enter parameter b: ");
        double c = promptDouble(scanner, "Enter parameter c: ");
        
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

        ParametricFunctionTabulator tabulator = new Variant17ParametricTabulator();
        List<TabulationPoint> result = tabulator.tabulate(xStart, xEnd, dx, a, b, c);

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
        String template = "| %-10.4f | %-14s |%n";
        String border = "+------------+----------------+";
        
        System.out.println();
        System.out.println(border);
        System.out.printf("| %-10s | %-14s |%n", "x", "F(x, a,b,c)");
        System.out.println(border);
        
        for (TabulationPoint p : points) {
            String yVal = Double.isNaN(p.y()) ? "Undefined(NaN)" : String.format("%.4f", p.y());
            System.out.printf(template, p.x(), yVal);
        }
        
        System.out.println(border);
        System.out.println();
    }
}