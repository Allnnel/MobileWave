package com.example.service.impl;

import com.example.exception.CustomException;
import com.example.model.UsersBlock;
import com.example.repository.UsersBlockRepository;
import com.example.service.BlockListService;
import com.example.service.UsersBlockService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersBlockServiceImpl implements UsersBlockService {

  private final UsersBlockRepository usersBlockRepository;
  private final BlockListService blockListService;

  @Autowired
  public UsersBlockServiceImpl(
      UsersBlockRepository usersBlockRepository, BlockListService blockListService) {
    this.usersBlockRepository = usersBlockRepository;
    this.blockListService = blockListService;
  }

  @Override
  @Transactional
  public Optional<UsersBlock> findByCtnAndBlockType(String blockType, String ctn)
      throws CustomException {
    Optional<UsersBlock> usersBlock = usersBlockRepository.findByCtnAndBlockType(ctn, blockType);
    if (!usersBlock.isPresent()) {
      throw new CustomException("CTN_NOT_BLOCKED", 2);
    }
    return usersBlock;
  }

  @Override
  @Transactional
  public List<UsersBlock> findByCtn(String ctn) throws CustomException {
    List<UsersBlock> usersBlock = usersBlockRepository.findByCtn(ctn);
    if (usersBlock.isEmpty()) {
      throw new CustomException("CTN_NOT_BLOCKED", 2);
    }
    return usersBlock;
  }

  @Override
  @Transactional
  public void save(UsersBlock usersBlock) throws CustomException {
    blockListService.findByBlockType(usersBlock.getBlockType());
    Optional<UsersBlock> usersBlockOptional =
        usersBlockRepository.findByCtnAndBlockType(usersBlock.getCtn(), usersBlock.getBlockType());
    if (!usersBlockOptional.isPresent()) {
      usersBlockRepository.save(usersBlock);
    } else {
      throw new CustomException("USER_ALREADY_EXISTS", 2);
    }
  }

  @Override
  @Transactional
  public void update(UsersBlock usersBlock) throws CustomException {
    blockListService.findByBlockType(usersBlock.getBlockType());
    Optional<UsersBlock> usersBlockOptional =
        usersBlockRepository.findByCtnAndBlockType(usersBlock.getCtn(), usersBlock.getBlockType());
    if (!usersBlockOptional.isPresent()) {
      save(usersBlock);
    } else {
      UsersBlock usersBlockClass = usersBlockOptional.get();
      usersBlockClass.setBlockType(usersBlock.getBlockType());
      usersBlockClass.setDescription(usersBlock.getDescription());
      usersBlockClass.setEndDate(usersBlock.getEndDate());
      usersBlockClass.setCtn(usersBlock.getCtn());
      usersBlockRepository.save(usersBlockClass);
    }
  }

  @Override
  @Transactional
  public void deleteByCtnAndBlockType(String ctn, String blockType) throws CustomException {
    findByCtnAndBlockType(blockType, ctn);
    usersBlockRepository.deleteByCtnAndBlockType(ctn, blockType);
  }
}
