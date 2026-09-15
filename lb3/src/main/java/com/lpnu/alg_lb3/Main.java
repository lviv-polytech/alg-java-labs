package com.lpnu.alg_lb3;

import com.lpnu.alg_lb2.util.InputLine;
import com.lpnu.alg_lb2.util.InputLineFactory;

public class Main {
  public static void main(String[] args) {
    try (InputLine in = InputLineFactory.console()) {
      var params = in.in("m=", "n=");
      System.out.println("m=" + params.m());
      System.out.println("n=" + params.n());
    }
  }
}
