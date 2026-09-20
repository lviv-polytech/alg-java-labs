package com.lpnu.lb6.service.task2;

import java.util.Random;

public class RecursiveMaxEvenService {

    private final Random random = new Random();

    public void generate(int[] array, int min, int max, int index) {
        if (index >= array.length) {
            return;
        }
        array[index] = random.nextInt(max - min + 1) + min;
        generate(array, min, max, index + 1);
    }

    public String formatArray(int[] array, int index) {
        if (index >= array.length) {
            return " ]";
        }
        String prefix = (index == 0) ? "[" : "";
        String node = String.format("%4d", array[index]);
        String suffix = (index < array.length - 1) ? "," : "";
        
        return prefix + node + suffix + formatArray(array, index + 1);
    }

    public int findMaxEvenIndex(int[] array) {
        int firstEvenIdx = findFirstEvenIndex(array, 0);
        if (firstEvenIdx == -1) {
            return -1;
        }
        return processRemainingElements(array, firstEvenIdx, firstEvenIdx + 1);
    }

    private int findFirstEvenIndex(int[] array, int index) {
        if (index >= array.length) {
            return -1;
        }
        if (array[index] % 2 == 0) {
            return index;
        }
        return findFirstEvenIndex(array, index + 1);
    }

    private int processRemainingElements(int[] array, int maxIdx, int currentIndex) {
        if (currentIndex >= array.length) {
            return maxIdx;
        }
        
        if (array[currentIndex] % 2 == 0 && array[currentIndex] > array[maxIdx]) {
            return processRemainingElements(array, currentIndex, currentIndex + 1);
        }
        
        return processRemainingElements(array, maxIdx, currentIndex + 1);
    }
}