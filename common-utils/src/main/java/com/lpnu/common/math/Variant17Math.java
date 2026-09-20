package com.lpnu.common.math;

/**
 * Contains decomposed mathematical utility functions corresponding 
 * to Variant 17 tasks to allow enterprise-level reuse across modules.
 */
public final class Variant17Math {

    private Variant17Math() {
        // Prevent instantiation
    }

    /**
     * Calculates the value of the next Taylor series term based on the previous term.
     * Prevents expensive power calculations using recurrent relations.
     * Formula: a_n = a_{n-1} * ((x-1)/x) * (n / (n+1))
     *
     * @param prevTerm the accumulated previous term (a_{n-1})
     * @param x        the argument of the function
     * @param n        the current step index
     * @return the calculated value for the current element (a_n)
     */
    public static double calculateNextTaylorTerm(double prevTerm, double x, int n) {
        return prevTerm * ((x - 1.0) / x) * ((double) n / (n + 1.0));
    }

    /**
     * Computes the Taylor Series for the natural logarithm function.
     * Integrates the recurrent term logic inside an iteration cycle to avoid overloads.
     *
     * @param x   the argument of the function (x > 0.5)
     * @param eps precision threshold
     * @return a TaylorResult containing the sum and the iteration count
     */
    public static TaylorResult computeLnTaylorSeries(double x, double eps) {
        if (x <= 0.5) {
            throw new IllegalArgumentException("Domain error: x must be > 0.5");
        }
        
        double currentA = (x - 1.0) / x;
        double sum = currentA;
        int n = 1;
        
        double nextA = calculateNextTaylorTerm(currentA, x, n);

        while (Math.abs(nextA) >= eps) {
            sum += nextA;
            n++;
            nextA = calculateNextTaylorTerm(nextA, x, n);
            
            // Safety limit to prevent memory locks for non-convergent cases
            if (n > 10_000_000) {
                break;
            }
        }
        
        return new TaylorResult(sum, n);
    }
}