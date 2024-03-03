package com.example.exception;

/**
 * Класс для пользовательского исключения, используемого для обработки различных ситуаций. Если
 * баланс не найден, выбрасывает исключение. "TOKEN_ERROR" - code: 1. "BALANCE_NOT_FOUND" - code: 2.
 * "OPERATION_TYPE_NOT_FOUND" - code: 3. "CLIENT_IS_BLOCKED" - code: 4. "BALANCE_ALREADY_EXISTS" -
 * code: 5. "NEGATIVE_USER_BALANCE" - code: 6. "HISTORY_IS_EMPTY" - code: 7. "LIST_IS_EMPTY" - code:
 * 8. "OPERATION_ALREADY_CREATED" - code: 9. "OPERATION_NOT_FOUND" - code: 10.
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
