package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
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
}
