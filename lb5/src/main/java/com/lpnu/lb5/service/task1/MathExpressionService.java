package com.lpnu.lb5.service.task1;

/**
 * Service for computing a complex mathematical expression demonstrating 
 * user-defined function decomposition.
 */
public class MathExpressionService {

    /**
     * Reusable user-defined function h(x, y, z).
     * Calculates (x + y + z) / (x^2 + y^2 + z^2).
     * 
     * @param x first parameter
     * @param y second parameter
     * @param z third parameter
     * @return result of h(x, y, z)
     */
    public double calculateH(double x, double y, double z) {
        double denominator = (x * x) + (y * y) + (z * z);
        if (Math.abs(denominator) < 1e-12) {
            // Logically handling division by zero or extremely small numbers
            return Double.NaN;
        }
        double numerator = x + y + z;
        return numerator / denominator;
    }

    /**
     * Evaluates the main composite expression for the given parameters a and b.
     * Formula: ( h(a, b, 1) + h(1, a, b) ) / ( 1 + h(a^2 + b^2, 1, 0) )
     * 
     * @param a parameter a
     * @param b parameter b
     * @return the result of the composite expression
     */
    public double evaluateCompositeExpression(double a, double b) {
        double topFirst = calculateH(a, b, 1.0);
        double topSecond = calculateH(1.0, a, b);
        double numerator = topFirst + topSecond;

        double bottomHParamsFirst = (a * a) + (b * b);
        double bottomSum = 1.0 + calculateH(bottomHParamsFirst, 1.0, 0.0);

        return numerator / bottomSum;
    }
}