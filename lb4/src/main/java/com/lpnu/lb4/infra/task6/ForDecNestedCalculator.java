package com.lpnu.lb4.infra.task6;

import com.lpnu.lb4.service.task6.NestedSeriesCalculator;

public class ForDecNestedCalculator implements NestedSeriesCalculator {
    @Override
    public double calculate() {
        double outSum = 0.0;
        
        for (int k = 20; k >= 1; k--) {
            double inSum = 0.0;
            for (int i = k; i >= 1; i--) {
                inSum += Math.cos(i + k);
            }
            outSum += Math.sqrt(Math.pow(Math.sin(k), 2) + Math.abs(inSum));
        }
        
        return outSum;
    }

    @Override
    public String getAlgorithmName() {
        return "Nested for(--)";
    }
}

