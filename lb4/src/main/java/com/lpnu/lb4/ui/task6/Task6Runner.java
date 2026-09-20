package com.lpnu.lb4.ui.task6;

import com.lpnu.lb4.infra.task6.NestedCalculatorFactory;
import com.lpnu.lb4.service.task6.NestedSeriesCalculator;

import java.util.List;

public class Task6Runner {

    public void run() {
        System.out.println("--- Task 6: Nested Loops Series Calculation ---");

        List<NestedSeriesCalculator> calculators = NestedCalculatorFactory.getAllCalculators();
        
        System.out.println("\nResults for N = 20:");
        for (NestedSeriesCalculator calc : calculators) {
            double result = calc.calculate();
            System.out.printf("[%-30s] S = %.6f%n", calc.getAlgorithmName(), result);
        }
        
        System.out.println("\nAll 4 results above should be exactly identical.");
        System.out.println("Task 6 completed.\n");
    }
}

