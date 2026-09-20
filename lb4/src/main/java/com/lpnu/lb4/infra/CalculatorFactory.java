package com.lpnu.lb4.infra;

import com.lpnu.lb4.service.SeriesSumCalculator;
import java.util.List;
import java.util.Arrays;

/**
 * Factory class that provides access to all different loop implementations.
 * Enables the main module to retrieve all calculators without knowing concrete classes.
 */
public final class CalculatorFactory {
    
    private CalculatorFactory() {
        // Prevent instantiation
    }

    /**
     * @return a list containing four different strategies (while, do-while, for-inc, for-dec)
     */
    public static List<SeriesSumCalculator> getAllCalculators() {
        return Arrays.asList(
            new WhileSeriesSumCalculator(),
            new DoWhileSeriesSumCalculator(),
            new ForIncrementSeriesSumCalculator(),
            new ForDecrementSeriesSumCalculator()
        );
    }
}
