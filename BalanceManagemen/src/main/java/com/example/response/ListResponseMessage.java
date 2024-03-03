package com.example.response;

import com.example.model.UsersOperationList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

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

  public UsersOperationList getList() {
    return list;
  }

  public void setList(UsersOperationList list) {
    this.list = list;
  }

  public List<UsersOperationList> getLists() {
    return lists;
  }

  public void setLists(List<UsersOperationList> lists) {
    this.lists = lists;
  }

  public BigDecimal getUserIsActiveSumm() {
    return userIsActiveSumm;
  }

  public void setUserIsActiveSumm(BigDecimal userIsActiveSumm) {
    this.userIsActiveSumm = userIsActiveSumm;
  }

  public BigDecimal getUserNotActiveSumm() {
    return userNotActiveSumm;
  }

  public void setUserNotActiveSumm(BigDecimal userNotActiveSumm) {
    this.userNotActiveSumm = userNotActiveSumm;
  }
}
