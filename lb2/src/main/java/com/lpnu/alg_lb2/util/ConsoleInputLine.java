package com.lpnu.alg_lb2.util;

import static java.lang.System.out;

import com.lpnu.common.dto.InParams;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

class ConsoleInputLine implements InputLine {

  private final Scanner sc = new Scanner(System.in);

  ConsoleInputLine() {
    sc.useLocale(Locale.US);
  }

  @Override
  public InParams in(String firstMessage, String secondMessage) {
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

    return new InParams(first, second);
  }

  @Override
  public InParams in(String firstMessage) {
    return in(firstMessage, null);
  }

  @Override
  public InParams in() {
    return in("m=", "n=");
  }

  @Override
  public InParams inM(String message) {
    return in(message, null);
  }

  @Override
  public InParams inN(String message) {
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
