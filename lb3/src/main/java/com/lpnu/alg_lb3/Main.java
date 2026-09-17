package com.lpnu.alg_lb3;

import com.lpnu.alg_lb2.util.InputLine;
import com.lpnu.alg_lb2.util.InputLineFactory;
import com.lpnu.alg_lb3.util.Lb3ExpressionResult;
import com.lpnu.alg_lb3.var.SevenTeen;

public class Main {
  public static void main(String[] args) {
    try (InputLine in = InputLineFactory.console()) {
      var params = in.inM("m=");
      var variant = new SevenTeen();
      Lb3ExpressionResult result = variant.expression(params);
      System.out.println("Result (y) = " + result.y());
    }
  }
}
