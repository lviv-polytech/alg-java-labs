package com.lpnu.lb4.service.task6;

import com.lpnu.lb4.infra.task6.NestedCalculatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class NestedSeriesCalculatorsTest {

    @Test
    void testCalculatorsYieldIdenticalResults() {
        List<NestedSeriesCalculator> calculators = NestedCalculatorFactory.getAllCalculators();
        
        Double expectedResult = null;
        
        for (NestedSeriesCalculator calculator : calculators) {
            double actual = calculator.calculate();
            
            if (expectedResult == null) {
                expectedResult = actual;
            } else {
                Assertions.assertEquals(expectedResult, actual, 1e-9, 
                    "Calculator " + calculator.getAlgorithmName() + " mismatched!");
            }
        }
    }
}

