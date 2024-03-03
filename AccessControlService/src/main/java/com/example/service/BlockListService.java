package com.example.service;

import com.example.exception.CustomException;
import com.example.model.BlockList;

public interface BlockListService {
  BlockList findByBlockType(String blockType) throws CustomException;
}
