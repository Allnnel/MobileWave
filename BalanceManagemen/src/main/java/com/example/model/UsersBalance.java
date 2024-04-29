package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
