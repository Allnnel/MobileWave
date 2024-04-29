package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "systemToken", schema = "server")
public class SystemToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "systemToken", nullable = false)
  private String systemToken;

  @Column(name = "startDate", nullable = false)
  private Date startDate;

  @Column(name = "endDate", nullable = false)
  private Date endDate;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "system", nullable = false)
  private String system;

  public SystemToken() {}

  public SystemToken(
      String systemToken, Date startDate, Date endDate, String description, String system) {
    this.systemToken = systemToken;
    this.startDate = startDate;
    this.endDate = endDate;
    this.description = description;
    this.system = system;
  }
}
