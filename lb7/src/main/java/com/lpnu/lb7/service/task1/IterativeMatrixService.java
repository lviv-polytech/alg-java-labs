package com.lpnu.lb7.service.task1;

import java.util.Random;

public class IterativeMatrixService {

    private final Random random = new Random();

    public void generate(int[][] matrix, int min, int max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(max - min + 1) + min;
            }
        }
    }

    public String formatMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(String.format("%4d", matrix[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void sort(int[][] matrix) {
        int rows = matrix.length;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < rows - i - 1; j++) {
                if (shouldSwap(matrix[j], matrix[j + 1])) {
                    swapRows(matrix, j, j + 1);
                }
            }
        }
    }

    private boolean shouldSwap(int[] row1, int[] row2) {
        // Core conditions for swapping (Key 1: col 0, Key 2: col 1, Key 3: col 3) => ASC
        if (row1[0] != row2[0]) {
            return row1[0] > row2[0];
        }
        if (row1[1] != row2[1]) {
            return row1[1] > row2[1];
        }
        return row1[3] > row2[3];
    }

    private void swapRows(int[][] matrix, int r1, int r2) {
        int[] temp = matrix[r1];
        matrix[r1] = matrix[r2];
        matrix[r2] = temp;
    }

    public MatrixProcessResult process(int[][] matrix) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // Condition: value is odd OR not a multiple of 3
                if (matrix[i][j] % 2 != 0 || matrix[i][j] % 3 != 0) {
                    count++;
                    sum += matrix[i][j];
                    matrix[i][j] = 0;
                }
            }
        }
        return new MatrixProcessResult(count, sum);
    }
}