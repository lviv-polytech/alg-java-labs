package com.lpnu.lb4.infra.task6;

import com.lpnu.lb4.service.task6.NestedSeriesCalculator;

public class WhileNestedCalculator implements NestedSeriesCalculator {
    @Override
    public double calculate() {
        double outSum = 0.0;
        int k = 1;
        
        while (k <= 20) {
            double inSum = 0.0;
            int i = 1;
            while (i <= k) {
                inSum += Math.cos(i + k);
                i++;
            }
            outSum += Math.sqrt(Math.pow(Math.sin(k), 2) + Math.abs(inSum));
            k++;
        }
        
        return outSum;
    }

    @Override
    public String getAlgorithmName() {
        return "Nested while(...)";
    }
}

