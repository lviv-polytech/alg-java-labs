package com.lpnu.lb5.service.task3;

/**
 * Service for computing a composite mathematical expression using a custom piecewise 
 * function with branches and optimized finite loops.
 */
public class PiecewiseFunctionService {

    /**
     * Auxiliary piecewise function h(x).
     * <p>
     * Branch 1: If |x| >= 1, h(x) = (sin(x) + 1) / (1 + cos(x))
     * Branch 2: If |x| < 1,  h(x) = Sum_{i=0}^{5} x^i / (2i)!
     * <p>
     * Sum calculation avoids direct factorial and power operations by utilizing 
     * the recurrence relation: a_n = a_{n-1} * (x / ((2i-1) * 2i)).
     * 
     * @param x parameter
     * @return evaluated result of h(x)
     */
    public double evaluateH(double x) {
        if (Math.abs(x) >= 1.0) {
            double denominator = 1.0 + Math.cos(x);
            if (Math.abs(denominator) < 1e-12) {
                return Double.NaN;
            }
            return (Math.sin(x) + 1.0) / denominator;
        } else {
            double term = 1.0; // a_0 = 1
            double sum = 1.0;
            
            for (int i = 1; i <= 5; i++) {
                term = term * x / ((2.0 * i - 1.0) * (2.0 * i));
                sum += term;
            }
            
            return sum;
        }
    }

    /**
     * Evaluates the main composite expression for parameter g.
     * Formula: h(g^2 + 1) + h(g + h(1)) + 1
     * 
     * @param g parameter
     * @return the evaluated composite result
     */
    public double evaluateComposite(double g) {
        double val1 = evaluateH((g * g) + 1.0);
        
        double hOfOne = evaluateH(1.0);
        double val2 = evaluateH(g + hOfOne);
        
        return val1 + val2 + 1.0;
    }
}