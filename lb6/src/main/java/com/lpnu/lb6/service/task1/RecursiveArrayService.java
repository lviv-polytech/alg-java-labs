package com.lpnu.lb6.service.task1;

import java.util.Random;

public class RecursiveArrayService {
    
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
        String node = String.format("%3d", array[index]);
        String suffix = (index < array.length - 1) ? "," : "";
        
        return prefix + node + suffix + formatArray(array, index + 1);
    }

    public ArrayProcessResult processAndModify(int[] array, int index, int countAccumulator, int sumAccumulator) {
        if (index >= array.length) {
            return new ArrayProcessResult(countAccumulator, sumAccumulator);
        }

        int currentCount = countAccumulator;
        int currentSum = sumAccumulator;

        // Condition: value is multiple of 6 OR index is NOT multiple of 5
        if (array[index] % 6 == 0 || index % 5 != 0) {
            currentCount++;
            currentSum += array[index];
            array[index] = 0;
        }

        return processAndModify(array, index + 1, currentCount, currentSum);
    }
}