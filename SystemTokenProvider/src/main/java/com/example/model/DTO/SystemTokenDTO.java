package com.example.model.DTO;

import com.example.model.SystemToken;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
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

}
