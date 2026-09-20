package com.lpnu.lb4.infra.task2;

import com.lpnu.lb4.service.task2.FunctionTabulator;
import com.lpnu.lb4.service.task2.TabulationPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the piecewise function tabulation for Variant 17.
 * f(x) = x^2 + 1 + { 
 *     4x^2 - x^2 + x^2 - 2, x < 4
 *     sin(2x + 1),          4 <= x < 7
 *     ln(2x + e^(x+1)),     x >= 7
 * }
 */
public class PiecewiseFunctionTabulator implements FunctionTabulator {

    @Override
    public List<TabulationPoint> tabulate(double xStart, double xEnd, double dx) {
        if (dx <= 0) {
            throw new IllegalArgumentException("Step dX must be greater than 0");
        }
        if (xStart > xEnd) {
            throw new IllegalArgumentException("xStart cannot be greater than xEnd");
        }

        List<TabulationPoint> points = new ArrayList<>();

        // Loop traversing the interval [xStart, xEnd] with step dx.
        // We use a safe floating point loop by avoiding accumulating dx on x in the condition.
        // Instead, we compute the number of steps to avoid precision drift, OR we just use a while loop with a small epsilon.
        
        final double EPSILON = 1e-9;
        double x = xStart;
        
        while (x <= xEnd + EPSILON) {
            double y = evaluateFunction(x);
            points.add(new TabulationPoint(x, y));
            x += dx;
        }

        return points;
    }

    private double evaluateFunction(double x) {
        double base = Math.pow(x, 2) + 1;
        double customPart;

        if (x < 4.0) {
            // 4x^2 - x^2 + x^2 - 2
            customPart = 4 * Math.pow(x, 2) - Math.pow(x, 2) + Math.pow(x, 2) - 2;
        } else if (x >= 4.0 && x < 7.0) {
            // sin(2x + 1)
            customPart = Math.sin(2 * x + 1);
        } else {
            // ln(2x + e^(x+1))
            customPart = Math.log(2 * x + Math.exp(x + 1));
        }

        return base + customPart;
    }
}