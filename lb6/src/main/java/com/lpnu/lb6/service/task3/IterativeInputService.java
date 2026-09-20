package com.lpnu.lb6.service.task3;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Service for working with arrays iteratively using explicitly typed and generic methods.
 */
public class IterativeInputService {

    /**
     * Strongly typed array filling logic (Integer).
     */
    public void fillIntArray(Integer[] array, Supplier<String> reader, Consumer<String> writer, String arrayName) {
        for (int i = 0; i < array.length; i++) {
            while (true) {
                writer.accept(String.format("%s[%d] = ", arrayName, i));
                String input = reader.get();
                try {
                    array[i] = Integer.parseInt(input.trim());
                    break;
                } catch (NumberFormatException e) {
                    writer.accept("Invalid integer format. Try again.\n");
                }
            }
        }
    }

    /**
     * Generic array filling logic mapping to C++ templates.
     */
    public <T> void fillGenericArray(T[] array, Function<String, T> parser, Supplier<String> reader, Consumer<String> writer, String arrayName) {
        for (int i = 0; i < array.length; i++) {
            while (true) {
                writer.accept(String.format("%s[%d] = ", arrayName, i));
                String input = reader.get();
                try {
                    array[i] = parser.apply(input.trim());
                    break;
                } catch (IllegalArgumentException | NullPointerException e) { // catching general exceptions from parsers
                    writer.accept("Invalid format for the respective type. Try again.\n");
                }
            }
        }
    }

    /**
     * Generic formatter to output array to a neat string.
     */
    public <T> String formatArray(T[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i] == null ? "null" : array[i].toString());
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}