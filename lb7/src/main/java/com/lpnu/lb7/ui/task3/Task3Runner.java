package com.lpnu.lb7.ui.task3;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb7.service.task3.IterativeMatrixProcessingService;
import com.lpnu.lb7.service.task3.RecursiveMatrixProcessingService;

public class Task3Runner {

    public void run() {
        System.out.println("--- Task 3: Dynamic Matrix Diagonal Modification & Row Search ---");

        SafeScanner scanner = new SafeScanner();
        int n;
        while (true) {
            System.out.print("Enter square matrix dimension (n > 1): ");
            String line = scanner.nextLineOrNull();
            if (line == null) System.exit(0);
            try {
                n = Integer.parseInt(line.trim());
                if (n > 1) break;
                System.out.println("Error: Size must be at least 2.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        scanner.close();

        System.out.println("\n[ ITERATIVE APPROACH ]");
        // Emulating C++ "double** matrix = new double*[n]; for(...) matrix[i] = new double[n];"
        double[][] itMatrix = new double[n][n];
        IterativeMatrixProcessingService itService = new IterativeMatrixProcessingService();
        
        itService.generate(itMatrix, -10, 50);
        System.out.println("Original Matrix:");
        System.out.println(itService.formatMatrix(itMatrix));

        int firstNonPosRow = itService.findFirstNonPositiveRow(itMatrix);
        if (firstNonPosRow == -1) {
            System.out.println("Analytics: No row found consisting entirely of non-positive elements.");
        } else {
            System.out.printf("Analytics: First fully non-positive row is at index %d%n", firstNonPosRow);
        }

        System.out.println("\nExecuting Diagonal Maximum Build (Selection Swap Strategy)...");
        itService.buildMaxDiagonal(itMatrix);
        
        System.out.println("Modified Matrix (Diagonal holds highest values descending):");
        System.out.println(itService.formatMatrix(itMatrix));

        // Memory cleanup emulation
        for (int i = 0; i < n; i++) itMatrix[i] = null; // delete[] array[i];
        itMatrix = null; // delete[] array;

        System.out.println("\n-----------------------------------------------------------");

        System.out.println("\n[ RECURSIVE APPROACH ]");
        // Emulating C++ "double** matrix = new double*[n]; for(...) matrix[i] = new double[n];"
        double[][] recMatrix = new double[n][n];
        RecursiveMatrixProcessingService recService = new RecursiveMatrixProcessingService();
        
        recService.generate(recMatrix, -10, 50, 0, 0);
        System.out.println("Original Matrix:");
        System.out.println(recService.formatMatrix(recMatrix, 0, 0));

        int firstNonPosRowRec = recService.findFirstNonPositiveRow(recMatrix);
        if (firstNonPosRowRec == -1) {
            System.out.println("Analytics: No row found consisting entirely of non-positive elements.");
        } else {
            System.out.printf("Analytics: First fully non-positive row is at index %d%n", firstNonPosRowRec);
        }

        System.out.println("\nExecuting Diagonal Maximum Build (Selection Swap Strategy)...");
        recService.buildMaxDiagonal(recMatrix);
        
        System.out.println("Modified Matrix (Diagonal holds highest values descending):");
        System.out.println(recService.formatMatrix(recMatrix, 0, 0));

        // Memory cleanup emulation
        for (int i = 0; i < n; i++) recMatrix[i] = null; // delete[] array[i];
        recMatrix = null; // delete[] array;

        System.out.println("\nTask 3 completed.\n");
    }
}