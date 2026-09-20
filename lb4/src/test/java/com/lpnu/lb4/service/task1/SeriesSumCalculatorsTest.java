package com.lpnu.lb4.service.task1;

import com.lpnu.lb4.infra.task1.CalculatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/**
 * Unit tests verifying that all looping constructs return identical results
 * for the given series expression, and checking edge cases.
 */
class SeriesSumCalculatorsTest {

    private final List<SeriesSumCalculator> calculators = CalculatorFactory.getAllCalculators();

    @ParameterizedTest(name = "N = {0}")
    @ValueSource(ints = {1, 2, 5, 10, 100})
    void testCalculatorsYieldIdenticalResults(int n) {
        double expected = calculateExpectedManually(n);
        
        for (SeriesSumCalculator calculator : calculators) {
            double actual = calculator.calculate(n);
            // using delta for floating point comparison robustness
            Assertions.assertEquals(expected, actual, 1e-9, 
                "Calculator " + calculator.getLoopType() + " failed for N=" + n);
        }
    }

    @Test
    void testInvalidBoundThrowsException() {
        for (SeriesSumCalculator calculator : calculators) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(0),
                "Expected exception for N=0 on " + calculator.getLoopType());
            Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(-5),
                "Expected exception for N=-5 on " + calculator.getLoopType());
        }
    }

    /**
     * Reusable independent implementation used purely as a baseline oracle.
     */
    private double calculateExpectedManually(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += Math.sin(i) / (1 + Math.cos(i));
        }
        return sum;
    }
}
