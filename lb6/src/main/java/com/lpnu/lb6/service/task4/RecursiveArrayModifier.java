package com.lpnu.lb6.service.task4;

import java.util.Random;

public class RecursiveArrayModifier {
    private final Random random = new Random();

    public void generate(double[] array, int min, int max, int index) {
        if (index >= array.length) {
            if (array.length > 0 && random.nextInt(10) < 3) {
                array[random.nextInt(array.length)] = 0.0;
            }
            return;
        }
        array[index] = (double) (random.nextInt(max - min + 1) + min) / 2.0;
        generate(array, min, max, index + 1);
    }

    public String formatArray(double[] array, int index) {
        if (index >= array.length) {
            return " ]";
        }
        String prefix = (index == 0) ? "[" : "";
        String node = String.format("%7.2f", array[index]);
        String suffix = (index < array.length - 1) ? "," : "";
        
        return prefix + node + suffix + formatArray(array, index + 1);
    }

    public int countPositiveElements(double[] array, int index) {
        if (index >= array.length) return 0;
        int currentCount = (array[index] > 0.0) ? 1 : 0;
        return currentCount + countPositiveElements(array, index + 1);
    }

    public double sumAfterLastZero(double[] array) {
        int lastZero = findLastZero(array, array.length - 1);
        if (lastZero == -1) return 0.0;
        return sumFromIndex(array, lastZero + 1);
    }
    
    private int findLastZero(double[] array, int index) {
        if (index < 0) return -1;
        if (Math.abs(array[index] - 0.0) < 1e-9) return index;
        return findLastZero(array, index - 1);
    }
    
    private double sumFromIndex(double[] array, int index) {
        if (index >= array.length) return 0.0;
        return array[index] + sumFromIndex(array, index + 1);
    }

    public void stableRestructure(double[] array) {
        double[] temp = new double[array.length];
        
        int destIdx1 = fillMatching(array, temp, 0, 0);
        int destIdx2 = fillNonMatching(array, temp, 0, destIdx1);
        
        copyBack(temp, array, 0);
    }
    
    private int fillMatching(double[] array, double[] temp, int srcIdx, int destIdx) {
        if (srcIdx >= array.length) return destIdx;
        
        if ((int) array[srcIdx] <= 1) {
            temp[destIdx] = array[srcIdx];
            return fillMatching(array, temp, srcIdx + 1, destIdx + 1);
        }
        return fillMatching(array, temp, srcIdx + 1, destIdx);
    }
    
    private int fillNonMatching(double[] array, double[] temp, int srcIdx, int destIdx) {
        if (srcIdx >= array.length) return destIdx;
        
        if ((int) array[srcIdx] > 1) {
            temp[destIdx] = array[srcIdx];
            return fillNonMatching(array, temp, srcIdx + 1, destIdx + 1);
        }
        return fillNonMatching(array, temp, srcIdx + 1, destIdx);
    }
    
    private void copyBack(double[] temp, double[] array, int idx) {
        if (idx >= array.length) return;
        array[idx] = temp[idx];
        copyBack(temp, array, idx + 1);
    }
}