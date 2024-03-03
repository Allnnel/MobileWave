package com.example.exception;

import com.example.response.ResponseMessage;
import com.example.response.UsersBlockResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ResponseMessage> handleCustomException(CustomException ex) {
    ResponseMessage response =
        new UsersBlockResponseMessage("Failed", ex.getMessage(), "500", false, null);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
