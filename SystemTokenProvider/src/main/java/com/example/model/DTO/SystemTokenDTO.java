package com.example.model.DTO;

import com.example.model.SystemToken;
import java.util.Date;

public class SystemTokenDTO {
  private String system;
  private Date startDate;
  private Date endDate;
  private String description;

  public SystemTokenDTO(SystemToken systemToken) {
    this.system = systemToken.getSystem();
    this.startDate = systemToken.getStartDate();
    this.endDate = systemToken.getEndDate();
    this.description = systemToken.getDescription();
  }

  public String getSystem() {
    return system;
  }

  public void setSystem(String system) {
    this.system = system;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
