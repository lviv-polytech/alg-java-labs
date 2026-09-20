package com.lpnu.lb4.infra.task7;

import com.lpnu.lb4.service.task7.TaylorRow;
import com.lpnu.lb4.service.task7.TaylorSeriesTabulator;

import java.util.ArrayList;
import java.util.List;

public class RecurrentTaylorTabulator implements TaylorSeriesTabulator {

    private static final double EPSILON_OFFSET = 1e-9;
    private static final int MAX_ITERATIONS = 10_000_000;

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
            // First term a_0
            double currentA = (x - 1.0) / x;
            double sum = currentA;
            
            // Loop counter to represent N
            int n = 1;
            
            // Calculate next term a_1 to check loop condition
            double nextA = currentA * ((x - 1.0) / x) * ((double) n / (n + 1.0));

            while (Math.abs(nextA) >= eps) {
                sum += nextA;
                n++;
                
                // Recurrence relation: a_n = a_{n-1} * ((x-1)/x) * (n / (n+1))
                nextA = nextA * ((x - 1.0) / x) * ((double) n / (n + 1.0));
                
                if (n > MAX_ITERATIONS) {
                    // Prevent infinite loops safely
                    break;
                }
            }

            // Built-in log comparison
            double mathLog = Math.log(x);
            
            // Note: number of calculated terms is n 
            // (if it didn't enter the loop, n=1 meaning 1 term: a_0)
            results.add(new TaylorRow(x, mathLog, sum, n));
            
            x += dx;
        }

        return results;
    }
}