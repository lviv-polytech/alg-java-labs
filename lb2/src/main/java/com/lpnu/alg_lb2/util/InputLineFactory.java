package com.lpnu.alg_lb2.util;

/** Factory for creating the package-private implementation behind the public contract. */
public final class InputLineFactory {

  private InputLineFactory() {}

  public static InputLine console() {
    return new ConsoleInputLine();
  }
}
