package com.example.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

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

  public String getCtn() {
    return ctn;
  }

  public void setCtn(String ctn) {
    this.ctn = ctn;
  }

  public String getBlockType() {
    return blockType;
  }

  public void setBlockType(String blockType) {
    this.blockType = blockType;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
