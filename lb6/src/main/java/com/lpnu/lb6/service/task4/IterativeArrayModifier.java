package com.lpnu.lb6.service.task4;

import java.util.Random;

public class IterativeArrayModifier {
    private final Random random = new Random();

    public void generate(double[] array, int min, int max) {
        for (int i = 0; i < array.length; i++) {
            // Generating discrete steps (-5 to +5) for clear zeroes, but as floating point math
            array[i] = (double) (random.nextInt(max - min + 1) + min) / 2.0; 
        }
        // Artificial zero insertion guarantee for testing 10% chance
        if (array.length > 0 && random.nextInt(10) < 3) {
            array[random.nextInt(array.length)] = 0.0;
        }
    }

    public String formatArray(double[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(String.format("%7.2f", array[i]));
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public int countPositiveElements(double[] array) {
        int count = 0;
        for (double v : array) {
            if (v > 0.0) count++;
        }
        return count;
    }

    public double sumAfterLastZero(double[] array) {
        int lastZero = -1;
        // Search backwards
        for (int i = array.length - 1; i >= 0; i--) {
            if (Math.abs(array[i] - 0.0) < 1e-9) {
                lastZero = i;
                break;
            }
        }
        
        if (lastZero == -1) return 0.0; // Task implicitly needs contingency if no zeros

        double sum = 0.0;
        for (int i = lastZero + 1; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public void stableRestructure(double[] array) {
        double[] temp = new double[array.length];
        int idx = 0;
        
        // Pass 1: Condition matched (int part <= 1)
        for (double v : array) {
            if ((int) v <= 1) {
                temp[idx++] = v;
            }
        }
        
        // Pass 2: Others
        for (double v : array) {
            if ((int) v > 1) {
                temp[idx++] = v;
            }
        }
        
        // Copy back
        for (int i = 0; i < array.length; i++) {
            array[i] = temp[i];
        }
    }
}