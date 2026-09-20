package com.lpnu.lb7.service.task3;

import java.util.Random;

public class IterativeMatrixProcessingService {

    private final Random random = new Random();

    public void generate(double[][] matrix, int min, int max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // Generate values between min and max
                matrix[i][j] = (double) (random.nextInt(max - min + 1) + min) / 2.0; 
            }
        }
        
        // Artificial insertion to guarantee a row without positives for testing occasionally
        if (matrix.length > 0 && random.nextInt(10) < 4) {
            int targetRow = random.nextInt(matrix.length);
            for (int j = 0; j < matrix[targetRow].length; j++) {
                matrix[targetRow][j] = -1.0 * Math.abs(matrix[targetRow][j]) - 1.0; 
            }
        }
    }

    public String formatMatrix(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(String.format("%8.2f", matrix[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Modifies the matrix IN-PLACE so the largest elements build the main diagonal descending.
     */
    public void buildMaxDiagonal(double[][] matrix) {
        int n = matrix.length;
        for (int k = 0; k < n; k++) {
            double currentMax = -Double.MAX_VALUE;
            int maxR = -1;
            int maxC = -1;

            // Find the maximum element ignoring previously locked diagonal elements [0..k-1]
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j && i < k) {
                        continue; // skip locked diagonal
                    }
                    if (matrix[i][j] > currentMax) {
                        currentMax = matrix[i][j];
                        maxR = i;
                        maxC = j;
                    }
                }
            }

            // Swap found maximum to the diagonal spot [k][k]
            if (maxR != -1 && maxC != -1) {
                double temp = matrix[k][k];
                matrix[k][k] = matrix[maxR][maxC];
                matrix[maxR][maxC] = temp;
            }
        }
    }

    /**
     * Finds the index of the first row containing no positive elements (<= 0.0)
     */
    public int findFirstNonPositiveRow(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            boolean hasPositive = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0.0) {
                    hasPositive = true;
                    break;
                }
            }
            if (!hasPositive) {
                return i;
            }
        }
        return -1;
    }
}