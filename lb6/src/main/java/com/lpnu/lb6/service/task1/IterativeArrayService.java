package com.lpnu.lb6.service.task1;

import java.util.Random;

public class IterativeArrayService {
    
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
            sb.append(String.format("%3d", array[i]));
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public ArrayProcessResult processAndModify(int[] array) {
        int count = 0;
        int sum = 0;
        
        for (int i = 0; i < array.length; i++) {
            // Condition: value is multiple of 6 OR index is NOT multiple of 5
            if (array[i] % 6 == 0 || i % 5 != 0) {
                count++;
                sum += array[i];
                array[i] = 0;
            }
        }
        
        return new ArrayProcessResult(count, sum);
    }
}