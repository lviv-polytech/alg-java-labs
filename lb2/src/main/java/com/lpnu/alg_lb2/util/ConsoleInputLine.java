package com.lpnu.alg_lb2.util;

import static java.lang.System.out;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

class ConsoleInputLine implements InputLine {

  private final Scanner sc = new Scanner(System.in);

  ConsoleInputLine() {
    sc.useLocale(Locale.US);
  }

  @Override
  public com.lpnu.alg_lb2.InParams in(String firstMessage, String secondMessage) {
    BigDecimal first = null;
    BigDecimal second = null;

    if (firstMessage != null) {
      out.println(firstMessage);
      first = readNumFromLine();
    }

    if (secondMessage != null) {
      out.println(secondMessage);
      second = readNumFromLine();
    }

    return new com.lpnu.alg_lb2.InParams(first, second);
  }

  @Override
  public com.lpnu.alg_lb2.InParams in(String firstMessage) {
    return in(firstMessage, null);
  }

  @Override
  public com.lpnu.alg_lb2.InParams in() {
    return in("m=", "n=");
  }

  @Override
  public com.lpnu.alg_lb2.InParams inM(String message) {
    return in(message, null);
  }

  @Override
  public com.lpnu.alg_lb2.InParams inN(String message) {
    return in(null, message);
  }

  private BigDecimal readNumFromLine() {
    var num = sc.nextBigDecimal();
    sc.nextLine();
    return num;
  }

  @Override
  public void close() {
    sc.close();
  }
}
