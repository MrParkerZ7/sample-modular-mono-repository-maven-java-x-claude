package com.example.common.exception;

/** Exception for business logic violations. */
public class BusinessException extends BaseException {

  public BusinessException(String message) {
    super("ERR_BUSINESS", message);
  }

  public BusinessException(String errorCode, String message) {
    super(errorCode, message);
  }

  public BusinessException(String errorCode, String message, Throwable cause) {
    super(errorCode, message, cause);
  }
}
