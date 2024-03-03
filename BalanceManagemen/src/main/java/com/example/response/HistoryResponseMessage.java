package com.example.response;

import com.example.model.UsersOperationHistory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryResponseMessage extends ResponseMessage {
  @JsonProperty("operationHistory")
  private List<UsersOperationHistory> history;

  public HistoryResponseMessage(
      String status, String message, String code, List<UsersOperationHistory> history) {
    super(status, message, code);
    this.history = history;
  }

  public List<UsersOperationHistory> getHistory() {
    return history;
  }

  public void setHistory(List<UsersOperationHistory> history) {
    this.history = history;
  }
}
