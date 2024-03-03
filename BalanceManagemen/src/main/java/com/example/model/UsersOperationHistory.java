package com.example.model;

import java.util.Date;
import javax.persistence.*;

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

  public String getCtn() {
    return ctn;
  }

  public void setCtn(String ctn) {
    this.ctn = ctn;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
