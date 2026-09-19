package com.lpnu.common.io;

import com.lpnu.common.dto.InParams;

/** Contract for reading values from a console-like input source. */
public interface InputLine extends AutoCloseable {

  InParams in();

  InParams in(String firstMessage, String secondMessage);

  InParams in(String firstMessage);

  InParams inM(String message);

  InParams inN(String message);

  @Override
  void close();
}
