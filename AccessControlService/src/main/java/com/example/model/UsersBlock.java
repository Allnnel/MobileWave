package com.example.model;

import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
@Getter
@Setter
@Entity
@Table(name = "usersBlock", schema = "server")
public class UsersBlock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ctn", nullable = false)
  private String ctn;

  @Column(name = "blockType", nullable = false)
  private String blockType;

  @Column(name = "startDate", nullable = false)
  private Date startDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "endDate", nullable = true)
  private Date endDate;

  @Column(name = "description", nullable = true)
  private String description;

  public UsersBlock(
      String ctn, String blockType, Date startDate, Date endDate, String description) {
    this.ctn = ctn;
    this.blockType = blockType;
    this.startDate = startDate;
    this.endDate = endDate;
    this.description = description;
  }

  public UsersBlock() {}
}
