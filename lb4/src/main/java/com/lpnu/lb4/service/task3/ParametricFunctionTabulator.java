package com.lpnu.lb4.service.task3;

import java.util.List;

/**
 * Contract for a service that tabulates a parametric mathematical function
 * over a given interval [xStart, xEnd] with a step dX and parameters a, b, c.
 */
public interface ParametricFunctionTabulator {

    /**
     * Tabulates the parametric function.
     *
     * @param xStart the start of the interval
     * @param xEnd   the end of the interval
     * @param dx     the step size
     * @param a      parameter a
     * @param b      parameter b
     * @param c      parameter c
     * @return a list of tabulated points (x, y)
     * @throws IllegalArgumentException if dx <= 0 or xStart > xEnd
     */
    List<TabulationPoint> tabulate(double xStart, double xEnd, double dx, double a, double b, double c);
}