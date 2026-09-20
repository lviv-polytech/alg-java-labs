package com.lpnu.lb4.ui;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.infra.CalculatorFactory;
import com.lpnu.lb4.service.SeriesSumCalculator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Lab 4: Trigonometric Series Sum (4 loop types) ---");
        
        SafeScanner scanner = new SafeScanner();
        int n = 0;
        
        while (true) {
            System.out.print("Enter upper bound N (positive integer >= 1): ");
            String line = scanner.nextLineOrNull();
            if (line == null) {
                System.out.println("\nApplication terminated.");
                return;
            }
            try {
                n = Integer.parseInt(line.trim());
                if (n < 1) {
                    System.out.println("Error: N must be >= 1. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Try again.");
            }
        }
        
        scanner.close();
        
        List<SeriesSumCalculator> calculators = CalculatorFactory.getAllCalculators();
        
        System.out.println("\nResults for N = " + n + ":");
        for (SeriesSumCalculator calc : calculators) {
            double result = calc.calculate(n);
            System.out.printf("[%-25s] S = %.6f%n", calc.getLoopType(), result);
        }
        
        System.out.println("\nAll 4 results above should be exactly identical.");
    }
}
