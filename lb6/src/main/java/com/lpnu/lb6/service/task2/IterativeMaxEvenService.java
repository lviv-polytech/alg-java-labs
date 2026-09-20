package com.lpnu.lb6.service.task2;

import java.util.Random;

public class IterativeMaxEvenService {

    private final Random random = new Random();

    public void generate(int[] array, int min, int max) {
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
    }

    public String formatArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(String.format("%4d", array[i]));
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public int findMaxEvenIndex(int[] array) {
        int maxIdx = -1;

        // Step 1: Find the first even element
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                maxIdx = i;
                break;
            }
        }

        // If there are no even elements at all
        if (maxIdx == -1) {
            return -1;
        }

        // Step 2: Compare remaining elements to the current maximum even element
        for (int i = maxIdx + 1; i < array.length; i++) {
            if (array[i] % 2 == 0 && array[i] > array[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }
}