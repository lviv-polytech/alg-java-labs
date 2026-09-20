package com.lpnu.lb6.ui.task4;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb6.service.task4.IterativeArrayModifier;
import com.lpnu.lb6.service.task4.RecursiveArrayModifier;

public class Task4Runner {

    public void run() {
        System.out.println("--- Task 4: Dynamic 1D Arrays Analysis & Stability ---");

        SafeScanner scanner = new SafeScanner();
        int n;
        while (true) {
            System.out.print("Enter size of dynamic array (n > 0): ");
            String line = scanner.nextLineOrNull();
            if (line == null) System.exit(0);
            try {
                n = Integer.parseInt(line.trim());
                if (n > 0) break;
                System.out.println("Error: Size must be at least 1.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        scanner.close();

        System.out.println("\n[ ITERATIVE APPROACH ]");
        // Emulating C++ "double* array = new double[n];"
        double[] itArray = new double[n];
        IterativeArrayModifier itService = new IterativeArrayModifier();
        
        itService.generate(itArray, -5, 5);
        System.out.println("Original Array:");
        System.out.println(itService.formatArray(itArray));
        
        System.out.println("\n--- Analytics ---");
        System.out.printf("Count of strict positives: %d%n", itService.countPositiveElements(itArray));
        System.out.printf("Sum of elements after last zero: %.2f%n", itService.sumAfterLastZero(itArray));
        
        System.out.println("\n--- Modification (Stable Partitioning) ---");
        itService.stableRestructure(itArray);
        System.out.println("Restructured Array (<=1 int part first):");
        System.out.println(itService.formatArray(itArray));

        // Emulating C++ "delete[] array;"
        itArray = null;

        System.out.println("\n-----------------------------------------------------------");

        System.out.println("\n[ RECURSIVE APPROACH ]");
        // Emulating C++ "double* array = new double[n];"
        double[] recArray = new double[n];
        RecursiveArrayModifier recService = new RecursiveArrayModifier();
        
        recService.generate(recArray, -5, 5, 0);
        System.out.println("Original Array:");
        System.out.println(recService.formatArray(recArray, 0));
        
        System.out.println("\n--- Analytics ---");
        System.out.printf("Count of strict positives: %d%n", recService.countPositiveElements(recArray, 0));
        System.out.printf("Sum of elements after last zero: %.2f%n", recService.sumAfterLastZero(recArray));
        
        System.out.println("\n--- Modification (Stable Partitioning) ---");
        recService.stableRestructure(recArray);
        System.out.println("Restructured Array (<=1 int part first):");
        System.out.println(recService.formatArray(recArray, 0));

        // Emulating C++ "delete[] array;" (Garbage Collector sweeps unreferenced memory dynamically)
        recArray = null;

        System.out.println("\nTask 4 completed.\n");
    }
}