package com.lpnu.lb6.ui.task2;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb6.service.task2.IterativeMaxEvenService;
import com.lpnu.lb6.service.task2.RecursiveMaxEvenService;

import java.util.Arrays;

public class Task2Runner {

    public void run() {
        System.out.println("--- Task 2: Finding Index of Max Even Element ---");

        SafeScanner scanner = new SafeScanner();
        int n;
        while (true) {
            System.out.print("Enter size of array (n > 0): ");
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
        
        System.out.print("Enter minimum random bound: ");
        int min = Integer.parseInt(scanner.nextLineOrNull().trim());
        
        System.out.print("Enter maximum random bound: ");
        int max = Integer.parseInt(scanner.nextLineOrNull().trim());
        
        scanner.close();
        
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        System.out.println("\n[ ITERATIVE APPROACH ]");
        IterativeMaxEvenService itService = new IterativeMaxEvenService();
        int[] iterativeArray = new int[n];
        itService.generate(iterativeArray, min, max);
        System.out.println(itService.formatArray(iterativeArray));
        
        int itIdx = itService.findMaxEvenIndex(iterativeArray);
        if (itIdx == -1) {
            System.out.println("Result: No even elements found in the array.");
        } else {
            System.out.printf("Result: Max Even Value = %d at Index = %d%n", iterativeArray[itIdx], itIdx);
        }

        System.out.println("\n-----------------------------------------------------------");

        System.out.println("\n[ RECURSIVE APPROACH ]");
        RecursiveMaxEvenService recService = new RecursiveMaxEvenService();
        int[] recursiveArray = new int[n];
        recService.generate(recursiveArray, min, max, 0);
        System.out.println(recService.formatArray(recursiveArray, 0));
        
        int recIdx = recService.findMaxEvenIndex(recursiveArray);
        if (recIdx == -1) {
            System.out.println("Result: No even elements found in the array.");
        } else {
            System.out.printf("Result: Max Even Value = %d at Index = %d%n", recursiveArray[recIdx], recIdx);
        }

        System.out.println("\nTask 2 completed.\n");
    }
}