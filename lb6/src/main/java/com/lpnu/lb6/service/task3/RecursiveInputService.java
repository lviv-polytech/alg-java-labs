package com.lpnu.lb6.service.task3;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Service for working with arrays recursively using explicitly typed and generic methods.
 */
public class RecursiveInputService {

    /**
     * Strongly typed array filling logic (Integer) using recursion.
     */
    public void fillIntArray(Integer[] array, Supplier<String> reader, Consumer<String> writer, String arrayName, int index) {
        if (index >= array.length) {
            return;
        }

        while (true) {
            writer.accept(String.format("%s[%d] = ", arrayName, index));
            String input = reader.get();
            try {
                array[index] = Integer.parseInt(input.trim());
                break;
            } catch (NumberFormatException e) {
                writer.accept("Invalid integer format. Try again.\n");
            }
        }
        
        fillIntArray(array, reader, writer, arrayName, index + 1);
    }

    /**
     * Generic array filling logic mapping to C++ templates via recursion.
     */
    public <T> void fillGenericArray(T[] array, Function<String, T> parser, Supplier<String> reader, Consumer<String> writer, String arrayName, int index) {
        if (index >= array.length) {
            return;
        }

        while (true) {
            writer.accept(String.format("%s[%d] = ", arrayName, index));
            String input = reader.get();
            try {
                array[index] = parser.apply(input.trim());
                break;
            } catch (IllegalArgumentException | NullPointerException e) {
                writer.accept("Invalid format for the respective type. Try again.\n");
            }
        }
        
        fillGenericArray(array, parser, reader, writer, arrayName, index + 1);
    }

    /**
     * Generic recursive formatter to output array to a neat string.
     */
    public <T> String formatArray(T[] array, int index) {
        if (index >= array.length) {
            return " ]";
        }
        
        String prefix = (index == 0) ? "[ " : "";
        String node = array[index] == null ? "null" : array[index].toString();
        String suffix = (index < array.length - 1) ? ", " : "";
        
        return prefix + node + suffix + formatArray(array, index + 1);
    }
}