package com.amc.exceptions;

public class BadRequestException extends RuntimeException {
  private boolean isObject = false;

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, boolean isObject) {
    super(message);
    this.isObject = isObject;
  }

  public boolean isObject() {
    return isObject;
  }
}
