package com.example.response;

import com.example.model.UsersBlock;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersBlockResponseMessage extends ResponseMessage {
  @JsonProperty("clientBlocked")
  private boolean clientBlocked;

  @JsonProperty("blockList")
  private List<UsersBlock> blockList;

  public UsersBlockResponseMessage(
      String status,
      String message,
      String code,
      boolean clientBlocked,
      List<UsersBlock> blockList) {
    super(status, message, code);
    this.clientBlocked = clientBlocked;
    this.blockList = blockList;
  }

  public boolean isClientBlocked() {
    return clientBlocked;
  }

  public void setClientBlocked(boolean clientBlocked) {
    this.clientBlocked = clientBlocked;
  }

  public List<UsersBlock> getBlockList() {
    return blockList;
  }

  public void setBlockList(List<UsersBlock> blockList) {
    this.blockList = blockList;
  }
}
