package com.example.service;

import com.example.exception.CustomException;
import com.example.model.UsersBlock;
import java.util.List;
import java.util.Optional;

public interface UsersBlockService {
  List<UsersBlock> findByCtn(String ctn) throws CustomException;

  Optional<UsersBlock> findByCtnAndBlockType(String blockType, String ctn) throws CustomException;

  void save(UsersBlock usersBlock) throws CustomException;

  void update(UsersBlock usersBlock) throws CustomException;

  void deleteByCtnAndBlockType(String ctn, String blockType) throws CustomException;
}
