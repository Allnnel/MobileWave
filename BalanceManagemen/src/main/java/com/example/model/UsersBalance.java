package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "usersBalance", schema = "server")
public class UsersBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ctn", nullable = false)
  private String ctn;

  @Column(name = "balance", nullable = false)
  private Double balance;

  public UsersBalance() {}

  public UsersBalance(String ctn, Double balance) {
    this.ctn = ctn;
    this.balance = balance;
  }

  public String getCtn() {
    return ctn;
  }

  public void setCtn(String ctn) {
    this.ctn = ctn;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
