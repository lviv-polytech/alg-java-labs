package com.lpnu.alg_lb3.var.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lpnu.alg_lb3.var.task2.PiecewiseFunction;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PiecewiseEdgeCasesTest {

  private final PiecewiseFunction fn = new PiecewiseFunction();

  @Test
  void firstCase_computes_quadratic_expression() {
    var a = BigDecimal.valueOf(2);
    var b = BigDecimal.valueOf(1);
    var c = BigDecimal.valueOf(3);
    var x = BigDecimal.valueOf(-20); // x + 10 < 0

    var res = fn.evaluate(a, b, c, x);

    assertEquals(a.multiply(x.pow(2)).subtract(c.multiply(x)).add(b), res);
  }

  @Test
  void secondCase_divide_by_zero_throws_when_x_equals_c() {
    var a = BigDecimal.valueOf(1);
    var b = BigDecimal.ZERO;
    var c = BigDecimal.valueOf(5);
    var x = BigDecimal.valueOf(5); // x - c == 0

    assertThrows(ArithmeticException.class, () -> fn.evaluate(a, b, c, x));
  }

  @Test
  void thirdCase_divide_by_zero_in_denominator_a_minus_c() {
    var a = BigDecimal.valueOf(5);
    var b = BigDecimal.valueOf(2);
    var c = BigDecimal.valueOf(5);
    var x = BigDecimal.valueOf(0); // falls into third case

    assertThrows(ArithmeticException.class, () -> fn.evaluate(a, b, c, x));
  }
}
