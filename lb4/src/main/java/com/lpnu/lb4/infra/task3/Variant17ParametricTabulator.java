package com.lpnu.lb4.infra.task3;

import com.lpnu.lb4.service.task3.ParametricFunctionTabulator;
import com.lpnu.lb4.service.task3.TabulationPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the parametric function tabulation for Variant 17.
 * F = {
 *     a*x^3 - (x+b),   if x+10 < 0 and b = 0
 *     (x-a)/(x-c),     if x+10 > 0 and b = 0
 *     (x-c)/(a-c),     otherwise
 * }
 */
public class Variant17ParametricTabulator implements ParametricFunctionTabulator {

    private static final double EPSILON = 1e-9;

    @Override
    public List<TabulationPoint> tabulate(double xStart, double xEnd, double dx, double a, double b, double c) {
        if (dx <= 0) {
            throw new IllegalArgumentException("Step dX must be greater than 0");
        }
        if (xStart > xEnd) {
            throw new IllegalArgumentException("xStart cannot be greater than xEnd");
        }

        List<TabulationPoint> points = new ArrayList<>();
        double x = xStart;

        while (x <= xEnd + EPSILON) {
            double y = evaluateParametricFunction(x, a, b, c);
            points.add(new TabulationPoint(x, y));
            x += dx;
        }

        return points;
    }

    private double evaluateParametricFunction(double x, double a, double b, double c) {
        boolean bIsZero = Math.abs(b) < 1e-7;

        if (x + 10.0 < 0 && bIsZero) {
            return a * Math.pow(x, 3) - (x + b);
        } else if (x + 10.0 > 0 && bIsZero) {
            if (Math.abs(x - c) < EPSILON) {
                return Double.NaN; // Division by zero representation
            }
            return (x - a) / (x - c);
        } else {
            if (Math.abs(a - c) < EPSILON) {
                return Double.NaN;
            }
            return (x - c) / (a - c);
        }
    }
}