package com.example.exception;

/**
 * Класс для пользовательского исключения, используемого для обработки различных ситуаций. Если
 * баланс не найден, выбрасывает исключение. "TOKEN_NOT_FOUND" - code: 1.
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
