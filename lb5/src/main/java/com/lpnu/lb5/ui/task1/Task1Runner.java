package com.lpnu.lb5.ui.task1;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.service.task1.MathExpressionService;

public class Task1Runner {

    public void run() {
        System.out.println("--- Task 1: Custom Math Functions ---");

        SafeScanner scanner = new SafeScanner();
        
        double a = promptDouble(scanner, "Enter parameter a: ");
        double b = promptDouble(scanner, "Enter parameter b: ");
        
        MathExpressionService service = new MathExpressionService();
        double result = service.evaluateCompositeExpression(a, b);
        
        System.out.printf("Result of the expression evaluates to: %.6f%n", result);
        System.out.println("Task 1 completed.\n");
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
}