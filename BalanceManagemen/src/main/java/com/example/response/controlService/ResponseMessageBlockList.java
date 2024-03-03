package com.example.response.controlService;

import com.example.model.сontrolService.UsersBlock;
import java.util.List;

public class ResponseMessageBlockList {

  private String status;
  private String code;
  private String message;
  private boolean clientBlocked;
  private List<UsersBlock> blockList;

  public ResponseMessageBlockList() {}

  public ResponseMessageBlockList(
      String status,
      String code,
      String message,
      boolean clientBlocked,
      List<UsersBlock> blockList) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.clientBlocked = clientBlocked;
    this.blockList = blockList;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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
