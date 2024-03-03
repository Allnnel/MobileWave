package com.example.service.impl;

import com.example.exception.CustomException;
import com.example.model.BlockList;
import com.example.repository.BlockListRepository;
import com.example.service.BlockListService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockListServiceImpl implements BlockListService {
  private final BlockListRepository blockListRepository;

  @Autowired
  public BlockListServiceImpl(BlockListRepository blockListRepository) {
    this.blockListRepository = blockListRepository;
  }

  @Override
  public BlockList findByBlockType(String blockType) throws CustomException {
    Optional<BlockList> blockListOptional = blockListRepository.findByBlockType(blockType);
    if (!blockListOptional.isPresent()) {
      throw new CustomException("BLOCK_TYPE_NOT_FOUND", 2);
    }
    return blockListOptional.get();
  }
}
