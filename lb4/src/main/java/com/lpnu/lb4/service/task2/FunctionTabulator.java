package com.lpnu.lb4.service.task2;

import java.util.List;

/**
 * Contract for a service that tabulates a mathematical function
 * over a given interval [xStart, xEnd] with a step dX.
 */
public interface FunctionTabulator {

    /**
     * Tabulates the function.
     *
     * @param xStart the start of the interval
     * @param xEnd   the end of the interval
     * @param dx     the step size
     * @return a list of tabulated points (x, y)
     * @throws IllegalArgumentException if dx <= 0 or xStart > xEnd
     */
    List<TabulationPoint> tabulate(double xStart, double xEnd, double dx);
}