package com.lpnu.lb5.ui.task5;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.service.task5.GeometricProgressionService;
import com.lpnu.lb5.service.task5.RecursionContext;

public class Task5Runner {

    public void run() {
        System.out.println("--- Task 5: Geometric Progression and Recursion Depth ---");

        SafeScanner scanner = new SafeScanner();
        
        double b1 = promptDouble(scanner, "Enter first term (b1): ");
        double q = promptDouble(scanner, "Enter ratio (q): ");
        
        int n;
        while (true) {
            System.out.print("Enter target element index (n >= 1): ");
            String line = scanner.nextLineOrNull();
            if (line == null) System.exit(0);
            try {
                n = Integer.parseInt(line.trim());
                if (n >= 1) break;
                System.out.println("Error: n must be at least 1.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }
        
        scanner.close();

        GeometricProgressionService service = new GeometricProgressionService();

        System.out.println("\n[ Phase 1: Calculating b_" + n + " ]");
        RecursionContext bContext = new RecursionContext(System.out::println);
        double bn = service.calculateNthTerm(b1, q, n, 1, bContext);
        System.out.printf("=> Result: b_%d = %.4f%n", n, bn);
        System.out.printf("=> Max Recursion Depth: %d%n", bContext.getMaxDepth());

        System.out.println("\n[ Phase 2: Calculating S_" + n + " ]");
        RecursionContext sumContext = new RecursionContext(System.out::println);
        double sn = service.calculateSum(b1, q, n, 1, sumContext);
        System.out.printf("=> Result: S_%d = %.4f%n", n, sn);
        System.out.printf("=> Max Recursion Depth: %d%n", sumContext.getMaxDepth());

        System.out.println("\nTask 5 completed.\n");
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