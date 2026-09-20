package com.lpnu.lb4.service.task7;

import java.util.List;

/**
 * Contract for enumerating the approximate values of a function using Taylor Series.
 */
public interface TaylorSeriesTabulator {
    
    /**
     * Tabulates the log(x) function using recurrent Taylor Series.
     *
     * @param xStart starting interval point (must be > 0.5)
     * @param xEnd   ending interval point
     * @param dx     step size
     * @param eps    target precision
     * @return list of tabulation rows
     */
    List<TaylorRow> tabulate(double xStart, double xEnd, double dx, double eps);
}