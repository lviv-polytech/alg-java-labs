package com.lpnu.lb4.infra.task7;

import com.lpnu.common.math.TaylorResult;
import com.lpnu.common.math.Variant17Math;
import com.lpnu.lb4.service.task7.TaylorRow;
import com.lpnu.lb4.service.task7.TaylorSeriesTabulator;

import java.util.ArrayList;
import java.util.List;

public class RecurrentTaylorTabulator implements TaylorSeriesTabulator {

    private static final double EPSILON_OFFSET = 1e-9;

    @Override
    public List<TaylorRow> tabulate(double xStart, double xEnd, double dx, double eps) {
        if (xStart <= 0.5) {
            throw new IllegalArgumentException("Domain error: xStart must be > 0.5");
        }
        if (xStart > xEnd) {
            throw new IllegalArgumentException("xStart cannot be > xEnd");
        }
        if (dx <= 0) {
            throw new IllegalArgumentException("Step size dx must be > 0");
        }
        if (eps <= 0) {
            throw new IllegalArgumentException("Precision eps must be > 0");
        }

        List<TaylorRow> results = new ArrayList<>();
        double x = xStart;

        while (x <= xEnd + EPSILON_OFFSET) {
            // Reusing common mathematical logic encapsulated in common-utils
            TaylorResult calcResult = Variant17Math.computeLnTaylorSeries(x, eps);
            double mathLog = Math.log(x);
            
            results.add(new TaylorRow(x, mathLog, calcResult.sum(), calcResult.termsCount()));
            x += dx;
        }

        return results;
    }
}