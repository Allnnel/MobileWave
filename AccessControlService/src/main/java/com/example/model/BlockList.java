package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

}
