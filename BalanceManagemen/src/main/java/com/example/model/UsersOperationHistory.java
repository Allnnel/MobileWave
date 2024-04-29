package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "usersOperationHistory", schema = "server")
public class UsersOperationHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ctn", nullable = false)
  private String ctn;

  @Column(name = "operation", nullable = false)
  private String operation;

  @Column(name = "value", nullable = false)
  private Double value;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "date", nullable = false)
  private Date date;

  public UsersOperationHistory() {}

  public UsersOperationHistory(
      String ctn, String operation, Double value, String description, Date date) {
    this.ctn = ctn;
    this.operation = operation;
    this.value = value;
    this.description = description;
    this.date = date;
  }
}
