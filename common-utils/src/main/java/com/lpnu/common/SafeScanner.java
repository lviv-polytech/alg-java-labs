package com.lpnu.common;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Minimal safe console scanner used by lab modules. Keeps a single Scanner wrapper
 * and provides safe parsing helpers that do not throw on EOF unexpectedly.
 *
 * This class is intentionally small — extend with retry logic and validation as
 * needed inside this module.
 */
public final class SafeScanner {

  private final Scanner scanner = new Scanner(System.in);

  public SafeScanner() {
    scanner.useDelimiter("\\n");
  }

  /** Read a whole line or return null on EOF. */
  public String nextLineOrNull() {
    try {
      if (!scanner.hasNext()) return null;
      return scanner.nextLine();
    } catch (NoSuchElementException ex) {
      return null;
    }
  }

  public void close() {
    scanner.close();
  }
}
