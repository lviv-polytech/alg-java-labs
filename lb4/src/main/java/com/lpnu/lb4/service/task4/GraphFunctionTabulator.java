package com.lpnu.lb4.service.task4;

import java.util.List;

/**
 * Contract for a service that tabulates a mathematical function derived
 * from a visual graph depending on argument x and radius R.
 */
public interface GraphFunctionTabulator {

    /**
     * Tabulates the graph-based piecewise function.
     *
     * @param xStart the start of the interval
     * @param xEnd   the end of the interval
     * @param dx     the step size
     * @param r      the radius parameter (typically >= 0)
     * @return a list of tabulated points (x, y)
     * @throws IllegalArgumentException if dx <= 0, xStart > xEnd, or r < 0
     */
    List<TabulationPoint> tabulate(double xStart, double xEnd, double dx, double r);
}