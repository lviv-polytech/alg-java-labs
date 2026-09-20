package com.lpnu.lb7.ui.task2;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb7.service.task2.IterativeRowExtremesService;
import com.lpnu.lb7.service.task2.RecursiveRowExtremesService;

public class Task2Runner {

    public void run() {
        System.out.println("--- Task 2: Extreme Swaps in Adjacent Matrix Rows ---");

        SafeScanner scanner = new SafeScanner();
        
        int rows = parsePositiveInt("Enter number of rows (k > 1): ", scanner);
        int cols = parsePositiveInt("Enter number of columns (n > 0): ", scanner);
        System.out.println();

        System.out.println("[ ITERATIVE APPROACH ]");
        IterativeRowExtremesService itService = new IterativeRowExtremesService();
        int[][] itMatrix = new int[rows][cols];
        
        itService.generate(itMatrix, 10, 99);
        System.out.println("Original Matrix:");
        System.out.println(itService.formatMatrix(itMatrix));
        
        itService.swapExtremes(itMatrix);
        System.out.println("Modified Matrix (Swapped ROW(x) MIN with ROW(x+1) MAX):");
        System.out.println(itService.formatMatrix(itMatrix));

        System.out.println("-----------------------------------------------------------");

        System.out.println("[ RECURSIVE APPROACH ]");
        RecursiveRowExtremesService recService = new RecursiveRowExtremesService();
        int[][] recMatrix = new int[rows][cols];
        
        recService.generate(recMatrix, 10, 99, 0, 0);
        System.out.println("Original Matrix:");
        System.out.println(recService.formatMatrix(recMatrix, 0, 0));
        
        recService.swapExtremes(recMatrix);
        System.out.println("Modified Matrix (Swapped ROW(x) MIN with ROW(x+1) MAX):");
        System.out.println(recService.formatMatrix(recMatrix, 0, 0));

        System.out.println("Task 2 completed.\n");
    }

    private int parsePositiveInt(String prompt, SafeScanner scanner) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLineOrNull();
            if (line == null) System.exit(0);
            try {
                int val = Integer.parseInt(line.trim());
                if (val > 0) return val;
                System.out.println("Must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid format.");
            }
        }
    }
}