package com.lpnu.lb7.service.task2;

import java.util.Random;

public class IterativeRowExtremesService {

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

    /**
     * Swaps the maximum element of each logical EVEN row (index 1, 3...) 
     * with the minimum element of the preceding logical ODD row (index 0, 2...).
     */
    public void swapExtremes(int[][] matrix) {
        // Here, 'r' serves as the anchor index pointing to logically ODD rows (index 0, 2, 4...)
        for (int r = 0; r < matrix.length - 1; r += 2) {
            
            // 'minColIndex' explicitly stores the column location of the MINIMUM element in the current ODD math row (index r)
            int minColIndex = 0;
            for (int c = 1; c < matrix[r].length; c++) {
                if (matrix[r][c] < matrix[r][minColIndex]) {
                    minColIndex = c;
                }
            }

            // 'maxColIndex' explicitly stores the column location of the MAXIMUM element in the next EVEN math row (index r + 1)
            int maxColIndex = 0;
            for (int c = 1; c < matrix[r + 1].length; c++) {
                if (matrix[r + 1][c] > matrix[r + 1][maxColIndex]) {
                    maxColIndex = c;
                }
            }

            // Standard swapping logic between identified extremes
            int temp = matrix[r][minColIndex];
            matrix[r][minColIndex] = matrix[r + 1][maxColIndex];
            matrix[r + 1][maxColIndex] = temp;
        }
    }
}