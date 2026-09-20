package com.lpnu.lb4.infra.task6;

import com.lpnu.lb4.service.task6.NestedSeriesCalculator;
import java.util.Arrays;
import java.util.List;

public final class NestedCalculatorFactory {
    private NestedCalculatorFactory() {}

    public static List<NestedSeriesCalculator> getAllCalculators() {
        return Arrays.asList(
            new WhileNestedCalculator(),
            new DoWhileNestedCalculator(),
            new ForIncNestedCalculator(),
            new ForDecNestedCalculator()
        );
    }
}

