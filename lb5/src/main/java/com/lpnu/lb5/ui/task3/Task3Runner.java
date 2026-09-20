package com.lpnu.lb5.ui.task3;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.service.task3.PiecewiseFunctionService;

public class Task3Runner {

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

        System.out.println("\n+------------+----------------+");
        System.out.printf("| %-10s | %-14s |%n", "g", "Q(g)");
        System.out.println("+------------+----------------+");

        PiecewiseFunctionService service = new PiecewiseFunctionService();

        // Loop using step counter to avoid double-precision accumulation drifting
        for (int i = 0; i <= nSegments; i++) {
            double g = gStart + i * dg;
            double result = service.evaluateComposite(g);
            
            System.out.printf("| %-10.4f | %-14.6f |%n", g, result);
        }

        System.out.println("+------------+----------------+");
        System.out.println("Task 3 completed.\n");
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
}