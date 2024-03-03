package com.example.service;

import com.example.exception.CustomException;
import com.example.model.UsersBalance;

public interface UsersBalanceService {
  enum OperationEnum {
    plus,
    minus
  }

  UsersBalance findByCtn(String ctn) throws CustomException;

  void save(String ctn, Double balance) throws CustomException;

  OperationEnum isValidOperation(String operation) throws CustomException;

  void update(String ctn, Double value, String operation) throws CustomException;
}
