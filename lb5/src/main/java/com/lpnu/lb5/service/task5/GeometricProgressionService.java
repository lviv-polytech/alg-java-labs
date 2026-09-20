package com.lpnu.lb5.service.task5;

public class GeometricProgressionService {

    /**
     * Calculates the n-th term of a geometric progression recursively.
     * 
     * @param b1      first term
     * @param q       ratio
     * @param n       target term index (1-based)
     * @param level   current stack level of recursion
     * @param context tracker simulating pass-by-reference for depth logging
     * @return value of the n-th term
     */
    public double calculateNthTerm(double b1, double q, int n, int level, RecursionContext context) {
        context.registerLevel("b_n", level);
        
        if (n == 1) {
            return b1;
        }
        
        return calculateNthTerm(b1, q, n - 1, level + 1, context) * q;
    }

    /**
     * Calculates the sum of the first n terms of a geometric progression recursively.
     * Incorporates recursion for calculating individual terms natively.
     * 
     * @param b1      first term
     * @param q       ratio
     * @param n       target terms count
     * @param level   current stack level of recursion
     * @param context tracker simulating pass-by-reference for depth logging
     * @return sum of the first n terms
     */
    public double calculateSum(double b1, double q, int n, int level, RecursionContext context) {
        context.registerLevel("S_n", level);
        
        if (n == 1) {
            return b1;
        }
        
        // Calculate the n-th term to add to the sum.
        // We can pass a silent context or the same context. 
        // Using a silent context prevents console clutter from inner functions when analyzing sum explicitly.
        RecursionContext silentContext = new RecursionContext(null);
        double bn = calculateNthTerm(b1, q, n, 1, silentContext);
        
        return calculateSum(b1, q, n - 1, level + 1, context) + bn;
    }
}