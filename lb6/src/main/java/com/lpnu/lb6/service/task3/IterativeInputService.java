package com.lpnu.lb6.service.task3;

import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Service for working with array input and formatted output iteratively (Method 1). Supports both
 * ordinary (type-specialized for Integer) and generic/template methods.
 */
public class IterativeInputService {

  public static final int DEFAULT_FIELD_WIDTH = 8;

  // ==========================================================================
  // Part A: Ordinary Iterative Functions (Integer)
  // ==========================================================================

  /**
   * Reads an array of integers iteratively from a Scanner stream.
   *
   * @param array target array
   * @param scanner input Scanner
   * @param promptWriter optional prompt callback ("a[i] = ")
   */
  public void inputArray(Integer[] array, Scanner scanner, Consumer<String> promptWriter) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (array.length > 0) {
      Objects.requireNonNull(scanner, "Scanner cannot be null");
    }
    for (int i = 0; i < array.length; i++) {
      if (promptWriter != null) {
        promptWriter.accept(String.format("a[%d] = ", i));
      }
      array[i] = Integer.parseInt(scanner.next().trim());
    }
  }

  /**
   * Formats an array of integers iteratively with a fixed column width.
   *
   * @param array source array
   * @param fieldWidth formatting column width
   * @return formatted row string
   */
  public String formatArray(Integer[] array, int fieldWidth) {
    if (array == null) return "";
    StringBuilder sb = new StringBuilder();
    for (Integer val : array) {
      sb.append(String.format("%" + fieldWidth + "d", val));
    }
    return sb.toString();
  }

  /** Strongly typed array filling logic (Integer) using reader/writer callbacks. */
  public void fillIntArray(
      Integer[] array, Supplier<String> reader, Consumer<String> writer, String arrayName) {
    Objects.requireNonNull(array, "Array cannot be null");
    for (int i = 0; i < array.length; i++) {
      while (true) {
        if (writer != null) {
          writer.accept(String.format("%s[%d] = ", arrayName, i));
        }
        String input = reader.get();
        try {
          array[i] = Integer.parseInt(input.trim());
          break;
        } catch (NumberFormatException e) {
          if (writer != null) {
            writer.accept("Invalid integer format. Try again.\n");
          }
        }
      }
    }
  }

  // ==========================================================================
  // Part B: Generic / Template Iterative Functions
  // ==========================================================================

  /**
   * Reads an array of generic type T iteratively from a Scanner stream.
   *
   * @param <T> element type
   * @param array target array
   * @param scanner input Scanner
   * @param parser conversion function from string token to T
   * @param promptWriter optional prompt callback
   */
  public <T> void inputArrayTemplate(
      T[] array, Scanner scanner, Function<String, T> parser, Consumer<String> promptWriter) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (array.length > 0) {
      Objects.requireNonNull(scanner, "Scanner cannot be null");
      Objects.requireNonNull(parser, "Parser cannot be null");
    }
    for (int i = 0; i < array.length; i++) {
      if (promptWriter != null) {
        promptWriter.accept(String.format("a[%d] = ", i));
      }
      array[i] = parser.apply(scanner.next().trim());
    }
  }

  /**
   * Formats an array of generic type T iteratively with a fixed column width.
   *
   * @param <T> element type
   * @param array source array
   * @param fieldWidth formatting column width
   * @return formatted row string
   */
  public <T> String formatArrayTemplate(T[] array, int fieldWidth) {
    if (array == null) return "";
    StringBuilder sb = new StringBuilder();
    for (T val : array) {
      sb.append(String.format("%" + fieldWidth + "s", val == null ? "null" : val.toString()));
    }
    return sb.toString();
  }

  /**
   * Prints formatted array elements iteratively to a PrintStream.
   *
   * @param <T> element type
   * @param array source array
   * @param out output stream
   * @param fieldWidth column width
   */
  public <T> void printArrayTemplate(T[] array, PrintStream out, int fieldWidth) {
    if (out != null) {
      out.println(formatArrayTemplate(array, fieldWidth));
    }
  }

  /** Generic array filling logic mapping to C++ templates using callbacks. */
  public <T> void fillGenericArray(
      T[] array,
      Function<String, T> parser,
      Supplier<String> reader,
      Consumer<String> writer,
      String arrayName) {
    Objects.requireNonNull(array, "Array cannot be null");
    for (int i = 0; i < array.length; i++) {
      while (true) {
        if (writer != null) {
          writer.accept(String.format("%s[%d] = ", arrayName, i));
        }
        String input = reader.get();
        try {
          array[i] = parser.apply(input.trim());
          break;
        } catch (IllegalArgumentException | NullPointerException e) {
          if (writer != null) {
            writer.accept("Invalid format for the respective type. Try again.\n");
          }
        }
      }
    }
  }

  /** Formats array to a neat string representation. */
  public <T> String formatArray(T[] array) {
    if (array == null) return "[]";
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
