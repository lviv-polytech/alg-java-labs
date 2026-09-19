package com.lpnu.common.io;

/** Factory for creating the package-private implementation behind the public contract. */
public final class InputLineFactory {

  private InputLineFactory() {}

  public static InputLine console() {
    return new ConsoleInputLine();
  }
}
