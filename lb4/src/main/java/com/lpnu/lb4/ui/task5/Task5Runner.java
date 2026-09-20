package com.lpnu.lb4.ui.task5;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.infra.task5.Variant17ShapeHitTester;
import com.lpnu.lb4.service.task5.ShapeHitTester;

public class Task5Runner {

    public void run() {
        System.out.println("--- Task 5: Shape Hit Testing (Logical Expressions) ---");

        SafeScanner scanner = new SafeScanner();
        
        double r;
        while (true) {
            r = promptDouble(scanner, "Enter radius / scale parameter R (must be >= 0): ");
            if (r >= 0) break;
            System.out.println("Error: Radius cannot be negative.");
        }

        ShapeHitTester tester = new Variant17ShapeHitTester();

        System.out.println("\n[ Phase 1: 10 Manual Shots ]");
        for (int i = 1; i <= 10; i++) {
            System.out.println("Shot " + i + "/10:");
            double x = promptDouble(scanner, "  X: ");
            double y = promptDouble(scanner, "  Y: ");
            
            boolean hit = tester.isHit(x, y, r);
            System.out.printf("  -> Result: %s%n", hit ? "YES" : "NO");
        }

        System.out.println("\n[ Phase 2: 10 Automatic Random Shots ]");
        // Generate within [-R, R] range for both axes.
        for (int i = 1; i <= 10; i++) {
            double x = generateRandomCoordinate(r);
            double y = generateRandomCoordinate(r);
            
            boolean hit = tester.isHit(x, y, r);
            System.out.printf("Auto-shot %2d: (x=%8.4f, y=%8.4f) -> %s%n", i, x, y, hit ? "YES" : "NO");
        }

        System.out.println("\nTask 5 completed.");
        scanner.close();
    }
    
    private double generateRandomCoordinate(double r) {
        // Random between -r and r
        return -r + (Math.random() * (2 * r));
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