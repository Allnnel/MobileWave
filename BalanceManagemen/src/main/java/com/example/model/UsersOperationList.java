package com.example.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "usersOperationList", schema = "server")
public class UsersOperationList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "ctn", nullable = false)
  private String ctn;

  @Column(name = "operation", nullable = false)
  private String operation;

  @Column(name = "value", nullable = false)
  private Double value;

  @Column(name = "active", nullable = false)
  private Boolean active;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "dateLastWithdrawal", nullable = true)
  private Date dateLastWithdrawal;

  public UsersOperationList() {}

  public UsersOperationList(
      String name,
      String ctn,
      String operation,
      Double value,
      Boolean active,
      String description,
      Date dateLastWithdrawal) {
    this.name = name;
    this.ctn = ctn;
    this.operation = operation;
    this.value = value;
    this.active = active;
    this.description = description;
    this.dateLastWithdrawal = dateLastWithdrawal;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDateLastWithdrawal() {
    return dateLastWithdrawal;
  }

  public void setDateLastWithdrawal(Date dateLastWithdrawal) {
    this.dateLastWithdrawal = dateLastWithdrawal;
  }
}
