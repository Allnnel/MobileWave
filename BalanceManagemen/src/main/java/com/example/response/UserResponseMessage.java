package com.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseMessage extends ResponseMessage {
  @JsonProperty("balance")
  private Double balance;

  public UserResponseMessage(String status, String message, String code, Double balance) {
    super(status, message, code);
    this.balance = balance;
  }

}
