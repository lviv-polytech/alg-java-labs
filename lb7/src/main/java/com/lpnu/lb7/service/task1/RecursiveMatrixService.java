package com.lpnu.lb7.service.task1;

import java.util.Random;

public class RecursiveMatrixService {

    private final Random random = new Random();

    public void generate(int[][] matrix, int min, int max, int row, int col) {
        if (row >= matrix.length) {
            return; // Terminate recursion
        }
        if (col >= matrix[row].length) {
            generate(matrix, min, max, row + 1, 0);
            return;
        }
        
        matrix[row][col] = random.nextInt(max - min + 1) + min;
        generate(matrix, min, max, row, col + 1);
    }

    public String formatMatrix(int[][] matrix, int row, int col) {
        if (row >= matrix.length) {
            return "";
        }
        if (col >= matrix[row].length) {
            return "\n" + formatMatrix(matrix, row + 1, 0);
        }
        
        String cell = String.format("%4d", matrix[row][col]);
        return cell + formatMatrix(matrix, row, col + 1);
    }

    public void sort(int[][] matrix, int limit) {
        if (limit <= 1) {
            return;
        }
        bubbleUp(matrix, 0, limit - 1);
        sort(matrix, limit - 1);
    }
    
    private void bubbleUp(int[][] matrix, int current, int limit) {
        if (current >= limit) {
            return;
        }
        if (shouldSwap(matrix[current], matrix[current + 1])) {
            swapRows(matrix, current, current + 1);
        }
        bubbleUp(matrix, current + 1, limit);
    }

    private boolean shouldSwap(int[] row1, int[] row2) {
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

    public MatrixProcessResult process(int[][] matrix, int row, int col, int currentCount, int currentSum) {
        if (row >= matrix.length) {
            return new MatrixProcessResult(currentCount, currentSum);
        }
        if (col >= matrix[row].length) {
            return process(matrix, row + 1, 0, currentCount, currentSum);
        }

        int count = currentCount;
        int sum = currentSum;

        if (matrix[row][col] % 2 != 0 || matrix[row][col] % 3 != 0) {
            count++;
            sum += matrix[row][col];
            matrix[row][col] = 0;
        }

        return process(matrix, row, col + 1, count, sum);
    }
}