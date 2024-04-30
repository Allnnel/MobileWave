package com.example.response;

import com.example.model.DTO.SystemTokenDTO;
import com.example.model.SystemToken;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponseMessage extends ResponseMessage {

  @JsonProperty("systemToken")
  private SystemTokenDTO systemTokenDTO;

  public TokenResponseMessage(String status, String message, String code, SystemToken systemToken) {
    super(status, message, code);
    this.systemTokenDTO = new SystemTokenDTO(systemToken);
  }

}
