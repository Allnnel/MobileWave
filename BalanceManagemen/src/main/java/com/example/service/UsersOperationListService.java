package com.example.service;

import com.example.exception.CustomException;
import com.example.model.UsersOperationList;
import java.math.BigDecimal;
import java.util.List;

public interface UsersOperationListService {
  UsersOperationList findByCtnAndName(String ctn, String name) throws CustomException;

  void save(UsersOperationList usersOperationList) throws CustomException;

  void update(UsersOperationList usersOperationList) throws CustomException;

  void delete(String ctn, String name) throws CustomException;

  List<UsersOperationList> findByCtn(String ctn) throws CustomException;

  BigDecimal[] sumValues(String ctn);

  List<UsersOperationList> findByActive(Boolean active) throws CustomException;
}
