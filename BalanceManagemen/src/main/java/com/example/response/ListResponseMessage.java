package com.example.response;

import com.example.model.UsersOperationList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponseMessage extends ResponseMessage {
  @JsonProperty("usersOperationList")
  private UsersOperationList list;

  @JsonProperty("usersOperationLists")
  private List<UsersOperationList> lists;

  @JsonProperty("userIsActiveSumm")
  BigDecimal userIsActiveSumm;

  @JsonProperty("userNotActiveSumm")
  BigDecimal userNotActiveSumm;

  public ListResponseMessage(String status, String message, String code, UsersOperationList list) {
    super(status, message, code);
    this.list = list;
  }

  public ListResponseMessage(
      String status, String message, String code, List<UsersOperationList> lists) {
    super(status, message, code);
    this.lists = lists;
  }

  public ListResponseMessage(
      String status,
      String message,
      String code,
      BigDecimal userIsActiveSumm,
      BigDecimal userNotActiveSumm) {
    super(status, message, code);
    this.userIsActiveSumm = userIsActiveSumm;
    this.userNotActiveSumm = userNotActiveSumm;
  }

}
