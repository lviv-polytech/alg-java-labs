package com.lpnu.lb4.ui.task7;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.infra.task7.RecurrentTaylorTabulator;
import com.lpnu.lb4.service.task7.TaylorRow;
import com.lpnu.lb4.service.task7.TaylorSeriesTabulator;

import java.util.List;

public class Task7Runner {

    public void run() {
        System.out.println("--- Task 7: Taylor Series Recurrent Tabulation ---");

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

        TaylorSeriesTabulator tabulator = new RecurrentTaylorTabulator();
        List<TaylorRow> result = tabulator.tabulate(xStart, xEnd, dx, eps);

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

    private void printTable(List<TaylorRow> rows) {
        String template = "| %-10.4f | %-14.6f | %-14.6f | %-12d |%n";
        String border = "+------------+----------------+----------------+--------------+";
        
        System.out.println();
        System.out.println(border);
        System.out.printf("| %-10s | %-14s | %-14s | %-12s |%n", "x", "Math.log(x)", "Taylor Sum", "Terms (n)");
        System.out.println(border);
        
        for (TaylorRow r : rows) {
            System.out.printf(template, r.x(), r.mathLog(), r.taylorLog(), r.termsCount());
        }
        
        System.out.println(border);
        System.out.println();
    }
}