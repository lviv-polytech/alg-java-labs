package com.lpnu.lb7.ui.task1;

import com.lpnu.lb7.service.task1.IterativeMatrixService;
import com.lpnu.lb7.service.task1.MatrixProcessResult;
import com.lpnu.lb7.service.task1.RecursiveMatrixService;

public class Task1Runner {

    public void run() {
        System.out.println("--- Task 1: 2D Matrix multi-key sort ---");

        final int ROWS = 8;
        final int COLS = 5;
        final int MIN = 11;
        final int MAX = 64;

        System.out.println("\n[ ITERATIVE APPROACH ]");
        IterativeMatrixService itService = new IterativeMatrixService();
        int[][] itMatrix = new int[ROWS][COLS];
        
        itService.generate(itMatrix, MIN, MAX);
        System.out.println("Original Matrix:");
        System.out.println(itService.formatMatrix(itMatrix));
        
        itService.sort(itMatrix);
        System.out.println("Matrix Sorted by Multi-Key (col0, col1, col3):");
        System.out.println(itService.formatMatrix(itMatrix));
        
        MatrixProcessResult itRes = itService.process(itMatrix);
        System.out.printf("Criteria Match COUNT (odd or not div by 3): %d%n", itRes.count());
        System.out.printf("Criteria Match SUM: %d%n", itRes.sum());
        
        System.out.println("\nMatrix After Nullifying Matches:");
        System.out.println(itService.formatMatrix(itMatrix));


        System.out.println("\n-----------------------------------------------------------");


        System.out.println("\n[ RECURSIVE APPROACH ]");
        RecursiveMatrixService recService = new RecursiveMatrixService();
        int[][] recMatrix = new int[ROWS][COLS];
        
        recService.generate(recMatrix, MIN, MAX, 0, 0);
        System.out.println("Original Matrix:");
        System.out.println(recService.formatMatrix(recMatrix, 0, 0));
        
        recService.sort(recMatrix, ROWS);
        System.out.println("Matrix Sorted by Multi-Key (col0, col1, col3):");
        System.out.println(recService.formatMatrix(recMatrix, 0, 0));
        
        MatrixProcessResult recRes = recService.process(recMatrix, 0, 0, 0, 0);
        System.out.printf("Criteria Match COUNT (odd or not div by 3): %d%n", recRes.count());
        System.out.printf("Criteria Match SUM: %d%n", recRes.sum());
        
        System.out.println("\nMatrix After Nullifying Matches:");
        System.out.println(recService.formatMatrix(recMatrix, 0, 0));

        System.out.println("\nTask 1 completed.\n");
    }
}