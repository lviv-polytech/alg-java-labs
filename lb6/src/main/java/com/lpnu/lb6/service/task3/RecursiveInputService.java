package com.lpnu.lb6.service.task3;

import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Service for working with array input and formatted output recursively (Method 2). Supports both
 * ordinary (type-specialized for Integer) and generic/template methods.
 */
public class RecursiveInputService {

  public static final int DEFAULT_FIELD_WIDTH = 8;

  // ==========================================================================
  // Part A: Ordinary Recursive Functions (Integer)
  // ==========================================================================

  /**
   * Reads an array of integers recursively from a Scanner stream.
   *
   * @param array target array
   * @param index current index being populated (initial: 0)
   * @param scanner input Scanner
   * @param promptWriter optional prompt callback ("a[i] = ")
   */
  public void inputArray(
      Integer[] array, int index, Scanner scanner, Consumer<String> promptWriter) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (index >= array.length) {
      return;
    }
    Objects.requireNonNull(scanner, "Scanner cannot be null");
    if (promptWriter != null) {
      promptWriter.accept(String.format("a[%d] = ", index));
    }
    array[index] = Integer.parseInt(scanner.next().trim());
    inputArray(array, index + 1, scanner, promptWriter);
  }

  /**
   * Formats an array of integers recursively with a fixed column width.
   *
   * @param array source array
   * @param index current index (initial: 0)
   * @param fieldWidth formatting column width
   * @return formatted row string
   */
  public String formatArray(Integer[] array, int index, int fieldWidth) {
    if (array == null || index >= array.length) {
      return "";
    }
    return String.format("%" + fieldWidth + "d", array[index])
        + formatArray(array, index + 1, fieldWidth);
  }

  /** Strongly typed array filling logic (Integer) using recursion and callbacks. */
  public void fillIntArray(
      Integer[] array,
      Supplier<String> reader,
      Consumer<String> writer,
      String arrayName,
      int index) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (index >= array.length) {
      return;
    }

    while (true) {
      if (writer != null) {
        writer.accept(String.format("%s[%d] = ", arrayName, index));
      }
      String input = reader.get();
      try {
        array[index] = Integer.parseInt(input.trim());
        break;
      } catch (NumberFormatException e) {
        if (writer != null) {
          writer.accept("Invalid integer format. Try again.\n");
        }
      }
    }

    fillIntArray(array, reader, writer, arrayName, index + 1);
  }

  // ==========================================================================
  // Part B: Generic / Template Recursive Functions
  // ==========================================================================

  /**
   * Reads an array of generic type T recursively from a Scanner stream.
   *
   * @param <T> element type
   * @param array target array
   * @param index current index being populated (initial: 0)
   * @param scanner input Scanner
   * @param parser conversion function from string token to T
   * @param promptWriter optional prompt callback
   */
  public <T> void inputArrayTemplate(
      T[] array,
      int index,
      Scanner scanner,
      Function<String, T> parser,
      Consumer<String> promptWriter) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (index >= array.length) {
      return;
    }
    Objects.requireNonNull(scanner, "Scanner cannot be null");
    Objects.requireNonNull(parser, "Parser cannot be null");

    if (promptWriter != null) {
      promptWriter.accept(String.format("a[%d] = ", index));
    }
    array[index] = parser.apply(scanner.next().trim());
    inputArrayTemplate(array, index + 1, scanner, parser, promptWriter);
  }

  /**
   * Formats an array of generic type T recursively with a fixed column width.
   *
   * @param <T> element type
   * @param array source array
   * @param index current index (initial: 0)
   * @param fieldWidth formatting column width
   * @return formatted row string
   */
  public <T> String formatArrayTemplate(T[] array, int index, int fieldWidth) {
    if (array == null || index >= array.length) {
      return "";
    }
    String valStr = array[index] == null ? "null" : array[index].toString();
    return String.format("%" + fieldWidth + "s", valStr)
        + formatArrayTemplate(array, index + 1, fieldWidth);
  }

  /**
   * Prints formatted array elements recursively to a PrintStream.
   *
   * @param <T> element type
   * @param array source array
   * @param out output stream
   * @param fieldWidth column width
   */
  public <T> void printArrayTemplate(T[] array, PrintStream out, int fieldWidth) {
    if (out != null) {
      out.println(formatArrayTemplate(array, 0, fieldWidth));
    }
  }

  /** Generic array filling logic mapping to C++ templates via recursion. */
  public <T> void fillGenericArray(
      T[] array,
      Function<String, T> parser,
      Supplier<String> reader,
      Consumer<String> writer,
      String arrayName,
      int index) {
    Objects.requireNonNull(array, "Array cannot be null");
    if (index >= array.length) {
      return;
    }

    while (true) {
      if (writer != null) {
        writer.accept(String.format("%s[%d] = ", arrayName, index));
      }
      String input = reader.get();
      try {
        array[index] = parser.apply(input.trim());
        break;
      } catch (IllegalArgumentException | NullPointerException e) {
        if (writer != null) {
          writer.accept("Invalid format for the respective type. Try again.\n");
        }
      }
    }

    fillGenericArray(array, parser, reader, writer, arrayName, index + 1);
  }

  /** Generic recursive formatter to output array to a neat string. */
  public <T> String formatArray(T[] array, int index) {
    if (array == null || index >= array.length) {
      return " ]";
    }

    String prefix = (index == 0) ? "[ " : "";
    String node = array[index] == null ? "null" : array[index].toString();
    String suffix = (index < array.length - 1) ? ", " : "";

    return prefix + node + suffix + formatArray(array, index + 1);
  }
}
