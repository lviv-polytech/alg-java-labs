package com.lpnu.lb5.service.task4;

/**
 * Service to calculate mathematical sum using different recursive traversal methods.
 */
public class RecursiveSumService {

    /**
     * Term calculation encapsulated.
     * Formula: j * (N - j) / (j^2 + (N - j)^2)
     */
    private double term(int j, int n) {
        double top = (double) j * (n - j);
        double bottom = (double) (j * j) + ((n - j) * (n - j));
        return top / bottom;
    }

    /**
     * Method 1 (Descent, descending): Calculates sum during recursive descent. 
     * The argument j decreases from N to 2.
     * 
     * @param n           upper bound N
     * @param j           current stepping interval
     * @param accumulator running sum passed forward
     * @return calculated sum
     */
    public double sumDescentDescending(int n, int j, double accumulator) {
        if (j < 2) {
            return accumulator;
        }
        return sumDescentDescending(n, j - 1, accumulator + term(j, n));
    }

    /**
     * Method 2 (Descent, ascending): Calculates sum during recursive descent.
     * The argument j increases from 2 to N.
     * 
     * @param n           upper bound N
     * @param j           current stepping interval
     * @param accumulator running sum passed forward
     * @return calculated sum
     */
    public double sumDescentAscending(int n, int j, double accumulator) {
        if (j > n) {
            return accumulator;
        }
        return sumDescentAscending(n, j + 1, accumulator + term(j, n));
    }

    /**
     * Method 3 (Ascent, descending): Calculates sum on return (recursive ascent).
     * The parameter j descends from N to 2, computing term after traversing.
     * 
     * @param n upper bound N
     * @param j current stepping interval
     * @return calculated sum
     */
    public double sumAscentDescending(int n, int j) {
        if (j < 2) {
            return 0.0;
        }
        return term(j, n) + sumAscentDescending(n, j - 1);
    }

    /**
     * Method 4 (Ascent, ascending): Calculates sum on return (recursive ascent).
     * The parameter j ascends from 2 to N, computing term after traversing.
     * 
     * @param n upper bound N
     * @param j current stepping interval
     * @return calculated sum
     */
    public double sumAscentAscending(int n, int j) {
        if (j > n) {
            return 0.0;
        }
        return term(j, n) + sumAscentAscending(n, j + 1);
    }

    /**
     * Method 5 (Iterative): Benchmark loop for validation.
     * 
     * @param n upper bound
     * @return sum
     */
    public double sumIterative(int n) {
        double sum = 0.0;
        for (int j = 2; j <= n; j++) {
            sum += term(j, n);
        }
        return sum;
    }
}