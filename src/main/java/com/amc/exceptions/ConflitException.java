package com.amc.exceptions;

import lombok.Getter;

@Getter
public class ConflitException extends RuntimeException {
  private int code;

  public ConflitException(String message) {
    super(message);
  }
}
