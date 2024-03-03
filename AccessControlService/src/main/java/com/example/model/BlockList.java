package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "blockList", schema = "server")
public class BlockList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "blockType", nullable = false)
  private String blockType;

  @Column(name = "description", nullable = false)
  private String description;

  public BlockList(String blockType, String description) {
    this.blockType = blockType;
    this.description = description;
  }

  public BlockList() {}

  public String getBlockType() {
    return blockType;
  }

  public void setBlockType(String blockType) {
    this.blockType = blockType;
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
