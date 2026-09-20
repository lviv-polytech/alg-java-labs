package com.lpnu.lb4.infra.task6;

import com.lpnu.lb4.service.task6.NestedSeriesCalculator;

public class DoWhileNestedCalculator implements NestedSeriesCalculator {
    @Override
    public double calculate() {
        double outSum = 0.0;
        int k = 1;
        
        do {
            double inSum = 0.0;
            int i = 1;
            // The inner loop only runs if k >= 1. Since k starts at 1, do-while is safe here without an IF check.
            do {
                inSum += Math.cos(i + k);
                i++;
            } while (i <= k);
            
            outSum += Math.sqrt(Math.pow(Math.sin(k), 2) + Math.abs(inSum));
            k++;
        } while (k <= 20);
        
        return outSum;
    }

    @Override
    public String getAlgorithmName() {
        return "Nested do { ... } while(...)";
    }
}

