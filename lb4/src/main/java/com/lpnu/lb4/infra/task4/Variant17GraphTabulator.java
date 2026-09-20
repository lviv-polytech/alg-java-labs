package com.lpnu.lb4.infra.task4;

import com.lpnu.lb4.service.task4.GraphFunctionTabulator;
import com.lpnu.lb4.service.task4.TabulationPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabulation matching Variant 17 visual graph:
 * y = 1,                      x <= -1 - R
 * y = -sqrt(R^2 - (x+1)^2),   -1 - R < x <= -1
 * y = -R,                     -1 < x <= 2
 * y = (R/2) * (x - 4),        x > 2
 */
public class Variant17GraphTabulator implements GraphFunctionTabulator {

    private static final double EPSILON = 1e-9;

    @Override
    public List<TabulationPoint> tabulate(double xStart, double xEnd, double dx, double r) {
        if (dx <= 0) {
            throw new IllegalArgumentException("Step dX must be greater than 0");
        }
        if (xStart > xEnd) {
            throw new IllegalArgumentException("xStart cannot be greater than xEnd");
        }
        if (r < 0) {
            throw new IllegalArgumentException("Radius R cannot be negative");
        }

        List<TabulationPoint> points = new ArrayList<>();
        double x = xStart;

        while (x <= xEnd + EPSILON) {
            double y = evaluateGraphFunction(x, r);
            points.add(new TabulationPoint(x, y));
            x += dx;
        }

        return points;
    }

    private double evaluateGraphFunction(double x, double r) {
        if (x <= -1.0 - r) {
            return 1.0;
        } else if (x > -1.0 - r && x <= -1.0) {
            double underRoot = r * r - Math.pow(x + 1.0, 2);
            // Protect against negative values caused by floating point inaccuracies near boundary
            if (underRoot < 0.0) { 
                underRoot = 0.0;
            }
            return -Math.sqrt(underRoot);
        } else if (x > -1.0 && x <= 2.0) {
            return -r;
        } else {
            return (r / 2.0) * (x - 4.0);
        }
    }
}