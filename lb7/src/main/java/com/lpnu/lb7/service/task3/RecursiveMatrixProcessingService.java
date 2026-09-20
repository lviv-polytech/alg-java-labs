package com.lpnu.lb7.service.task3;

import java.util.Random;

public class RecursiveMatrixProcessingService {

    private final Random random = new Random();

    public void generate(double[][] matrix, int min, int max, int row, int col) {
        if (row >= matrix.length) {
            setupNegativeRowPossibility(matrix);
            return;
        }
        if (col >= matrix[row].length) {
            generate(matrix, min, max, row + 1, 0);
            return;
        }
        
        matrix[row][col] = (double) (random.nextInt(max - min + 1) + min) / 2.0;
        generate(matrix, min, max, row, col + 1);
    }
    
    // Extracted logic to not mess up recursion pureness on primary generation
    private void setupNegativeRowPossibility(double[][] matrix) {
        if (matrix.length > 0 && random.nextInt(10) < 4) {
            int targetRow = random.nextInt(matrix.length);
            makeRowNegative(matrix[targetRow], 0);
        }
    }
    
    private void makeRowNegative(double[] row, int col) {
        if (col >= row.length) return;
        row[col] = -1.0 * Math.abs(row[col]) - 1.0;
        makeRowNegative(row, col + 1);
    }

    public String formatMatrix(double[][] matrix, int row, int col) {
        if (row >= matrix.length) return "";
        if (col >= matrix[row].length) {
            return "\n" + formatMatrix(matrix, row + 1, 0);
        }
        
        String cell = String.format("%8.2f", matrix[row][col]);
        return cell + formatMatrix(matrix, row, col + 1);
    }

    public void buildMaxDiagonal(double[][] matrix) {
        stepDiagonal(matrix, 0);
    }

    private void stepDiagonal(double[][] matrix, int k) {
        if (k >= matrix.length) return;
        
        // Find max element ignoring locked [0..k-1] diagonals
        int[] maxCoords = findMaxCoordinates(matrix, k, 0, 0, new int[]{ -1, -1 }, -Double.MAX_VALUE);
        
        if (maxCoords[0] != -1 && maxCoords[1] != -1) {
            int r = maxCoords[0];
            int c = maxCoords[1];
            double temp = matrix[k][k];
            matrix[k][k] = matrix[r][c];
            matrix[r][c] = temp;
        }
        
        stepDiagonal(matrix, k + 1);
    }

    private int[] findMaxCoordinates(double[][] matrix, int k, int currR, int currC, int[] bestCoords, double bestVal) {
        if (currR >= matrix.length) {
            return bestCoords;
        }
        if (currC >= matrix[currR].length) {
            return findMaxCoordinates(matrix, k, currR + 1, 0, bestCoords, bestVal);
        }

        // Avoid the locked diagonal elements
        if (currR == currC && currR < k) {
            return findMaxCoordinates(matrix, k, currR, currC + 1, bestCoords, bestVal);
        }

        if (matrix[currR][currC] > bestVal) {
            bestCoords[0] = currR;
            bestCoords[1] = currC;
            return findMaxCoordinates(matrix, k, currR, currC + 1, bestCoords, matrix[currR][currC]);
        }

        return findMaxCoordinates(matrix, k, currR, currC + 1, bestCoords, bestVal);
    }

    public int findFirstNonPositiveRow(double[][] matrix) {
        return findRowRec(matrix, 0);
    }
    
    private int findRowRec(double[][] matrix, int row) {
        if (row >= matrix.length) return -1;
        
        boolean hasPos = hasPositiveInRow(matrix[row], 0);
        if (!hasPos) {
            return row;
        }
        return findRowRec(matrix, row + 1);
    }
    
    private boolean hasPositiveInRow(double[] rowArr, int col) {
        if (col >= rowArr.length) return false;
        if (rowArr[col] > 0.0) return true;
        return hasPositiveInRow(rowArr, col + 1);
    }
}