package com.lpnu.lb6.ui.task1;

import com.lpnu.lb6.service.task1.IterativeArrayService;
import com.lpnu.lb6.service.task1.RecursiveArrayService;

public class Task1Runner {

  public void run() {
    System.out.println(
        "--- Task 1: 1D Array Interventions (Iterative vs Recursive) - Variant 17 ---");

    final int ARRAY_SIZE = 25;
    final int MIN_VAL = 4;
    final int MAX_VAL = 73;

    System.out.println("\n[ ITERATIVE APPROACH ]");
    IterativeArrayService itService = new IterativeArrayService();
    int[] iterativeArray = new int[ARRAY_SIZE];

    itService.generate(iterativeArray, MIN_VAL, MAX_VAL);
    System.out.println("Original Array: ");
    System.out.println(itService.formatArray(iterativeArray));

    int itCount = itService.countElements(iterativeArray);
    int itSum = itService.sumElements(iterativeArray);
    itService.zeroElements(iterativeArray);

    System.out.printf("Criteria Matched Elements COUNT: %d%n", itCount);
    System.out.printf("Criteria Matched Elements SUM:   %d%n", itSum);
    System.out.println("Modified Array: ");
    System.out.println(itService.formatArray(iterativeArray));

    System.out.println("\n-----------------------------------------------------------");

    System.out.println("\n[ RECURSIVE APPROACH ]");
    RecursiveArrayService recService = new RecursiveArrayService();
    int[] recursiveArray = new int[ARRAY_SIZE];

    recService.generate(recursiveArray, MIN_VAL, MAX_VAL, 0);
    System.out.println("Original Array: ");
    System.out.println(recService.formatArray(recursiveArray, 0));

    int recCount = recService.countElements(recursiveArray, 0);
    int recSum = recService.sumElements(recursiveArray, 0);
    recService.zeroElements(recursiveArray, 0);

    System.out.printf("Criteria Matched Elements COUNT: %d%n", recCount);
    System.out.printf("Criteria Matched Elements SUM:   %d%n", recSum);
    System.out.println("Modified Array: ");
    System.out.println(recService.formatArray(recursiveArray, 0));

    System.out.println("\nTask 1 completed.\n");
  }
}
