package com.lpnu.alg_lb2.util;

/** Contract for reading values from a console-like input source. */
public interface InputLine extends AutoCloseable {

  com.lpnu.alg_lb2.InParams in();

  com.lpnu.alg_lb2.InParams in(String firstMessage, String secondMessage);

  com.lpnu.alg_lb2.InParams in(String firstMessage);

  com.lpnu.alg_lb2.InParams inM(String message);

  com.lpnu.alg_lb2.InParams inN(String message);

  @Override
  void close();
}
