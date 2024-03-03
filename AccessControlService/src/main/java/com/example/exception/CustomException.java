package com.example.exception;

/**
 * Класс для пользовательского исключения, используемого для обработки различных ситуаций. Если
 * баланс не найден, выбрасывает исключение. "TOKEN_ERROR" - code: 1. "CTN_NOT_BLOCKED" - code: 2.
 * "BLOCK_TYPE_NOT_FOUND" - code: 3. "CTN_ALREADY_BLOCKED" - code: 4.
 */
public class CustomException extends Exception {
  private final int errorCode;
  private final String message;

  public CustomException(String message, int errorCode) {
    super(message);
    this.errorCode = errorCode;
    this.message = message;
  }

  public int getErrorCode() {
    return errorCode;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
