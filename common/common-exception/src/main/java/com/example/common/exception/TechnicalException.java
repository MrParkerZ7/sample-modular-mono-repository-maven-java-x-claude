package com.example.common.exception;

/** Exception for technical/infrastructure errors. */
public class TechnicalException extends BaseException {

  public TechnicalException(String message) {
    super("ERR_TECHNICAL", message);
  }

  public TechnicalException(String errorCode, String message) {
    super(errorCode, message);
  }

  public TechnicalException(String errorCode, String message, Throwable cause) {
    super(errorCode, message, cause);
  }
}
