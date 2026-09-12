package com.lpnu.alg_lb2;

import static java.lang.System.out;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

class InputLine implements AutoCloseable {

  private final Scanner sc = new Scanner(System.in);

  InputLine() {
    sc.useLocale(Locale.US);
  }

  public InParams in() {
    out.println("Z1=");
    var z1 = readNumFromLine();
    out.println("Z2=");
    var z2 = readNumFromLine();
    return new InParams(z1, z2);
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
