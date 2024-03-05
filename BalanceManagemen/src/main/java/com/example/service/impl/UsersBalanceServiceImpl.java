package com.example.service.impl;

import com.example.exception.CustomException;
import com.example.model.UsersBalance;
import com.example.repository.UsersBalanceRepository;
import com.example.service.UsersBalanceService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UsersBalanceServiceImpl implements UsersBalanceService {

  private final UsersBalanceRepository usersBalanceRepository;

  @Autowired
  public UsersBalanceServiceImpl(UsersBalanceRepository usersBalanceRepository) {
    this.usersBalanceRepository = usersBalanceRepository;
  }

  @Override
  public UsersBalance findByCtn(String ctn) throws CustomException {
    Optional<UsersBalance> usersBalance = usersBalanceRepository.findByCtn(ctn);
    if (!usersBalance.isPresent()) {
      throw new CustomException("BALANCE_NOT_FOUND", 2);
    }
    return usersBalance.get();
  }

  @Override
  public void save(String ctn, Double balance) throws CustomException {
    Optional<UsersBalance> usersBalance = usersBalanceRepository.findByCtn(ctn);
    if (usersBalance.isPresent()) {
      throw new CustomException("BALANCE_ALREADY_EXISTS", 5);
    }
    UsersBalance userBalance = new UsersBalance(ctn, balance);
    usersBalanceRepository.save(userBalance);
  }

  @Override
  public OperationEnum isValidOperation(String operation) throws CustomException {
    try {
      return OperationEnum.valueOf(operation);
    } catch (IllegalArgumentException | NullPointerException e) {
      throw new CustomException("OPERATION_TYPE_NOT_FOUND", 3);
    }
  }

  @Override
  public void update(String ctn, Double value, String operation) throws CustomException {
    OperationEnum operationEnum = isValidOperation(operation);
    UsersBalance usersBalance = findByCtn(ctn);
    double balance = usersBalance.getBalance();
    if (operationEnum == OperationEnum.plus) {
      balance += value;
    } else if (operationEnum == OperationEnum.minus) {
      balance -= value;
    }
    usersBalance.setBalance(balance);
    usersBalanceRepository.save(usersBalance);
    if (balance < 0) throw new CustomException("NEGATIVE_USER_BALANCE", 6);
  }
}
