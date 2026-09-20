package com.lpnu.lb7.service.task2;

import java.util.Random;

public class RecursiveRowExtremesService {

    private final Random random = new Random();

    public void generate(int[][] matrix, int min, int max, int row, int col) {
        if (row >= matrix.length) return;
        if (col >= matrix[row].length) {
            generate(matrix, min, max, row + 1, 0);
            return;
        }
        
        matrix[row][col] = random.nextInt(max - min + 1) + min;
        generate(matrix, min, max, row, col + 1);
    }

    public String formatMatrix(int[][] matrix, int row, int col) {
        if (row >= matrix.length) return "";
        if (col >= matrix[row].length) {
            return "\n" + formatMatrix(matrix, row + 1, 0);
        }
        
        String cell = String.format("%4d", matrix[row][col]);
        return cell + formatMatrix(matrix, row, col + 1);
    }

    /**
     * Entry wrapper for recursive extreme swapping
     */
    public void swapExtremes(int[][] matrix) {
        processAdjacentPairs(matrix, 0);
    }

    /**
     * Recursively traverses adjacent rows.
     * @param r Index of the mathematical ODD row (0, 2, 4...)
     */
    private void processAdjacentPairs(int[][] matrix, int r) {
        // Stop recursion if we run out of valid pair partners
        if (r >= matrix.length - 1) return;

        // Obtain indices using dedicated recursive column scanners
        int minColIndex = findMinColIndex(matrix[r], 0, 0);
        int maxColIndex = findMaxColIndex(matrix[r + 1], 0, 0);

        // Perform mutual exchange isolated to identified boundaries
        int temp = matrix[r][minColIndex];
        matrix[r][minColIndex] = matrix[r + 1][maxColIndex];
        matrix[r + 1][maxColIndex] = temp;

        // Dive to the next pair (2 rows down)
        processAdjacentPairs(matrix, r + 2);
    }

    /**
     * Recursively finds the COLUMN INDEX possessing the MINIMUM value in the given row array.
     */
    private int findMinColIndex(int[] rowArray, int currentCol, int minColSoFar) {
        if (currentCol >= rowArray.length) {
            return minColSoFar;
        }
        if (rowArray[currentCol] < rowArray[minColSoFar]) {
            return findMinColIndex(rowArray, currentCol + 1, currentCol);
        }
        return findMinColIndex(rowArray, currentCol + 1, minColSoFar);
    }

    /**
     * Recursively finds the COLUMN INDEX possessing the MAXIMUM value in the given row array.
     */
    private int findMaxColIndex(int[] rowArray, int currentCol, int maxColSoFar) {
        if (currentCol >= rowArray.length) {
            return maxColSoFar;
        }
        if (rowArray[currentCol] > rowArray[maxColSoFar]) {
            return findMaxColIndex(rowArray, currentCol + 1, currentCol);
        }
        return findMaxColIndex(rowArray, currentCol + 1, maxColSoFar);
    }
}