package com.example.service;

import com.example.exception.CustomException;
import com.example.model.UsersOperationList;
import com.example.repository.UsersOperationListRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersOperationListServiceImpl implements UsersOperationListService {
  private final UsersOperationListRepository repository;

  @Autowired
  public UsersOperationListServiceImpl(UsersOperationListRepository repository) {
    this.repository = repository;
  }

  @Override
  public UsersOperationList findByCtnAndName(String ctn, String name) throws CustomException {
    UsersOperationList usersOperationLists = repository.findByCtnAndName(ctn, name);
    if (usersOperationLists == null) {
      throw new CustomException("LIST_IS_EMPTY", 8);
    }
    return usersOperationLists;
  }

  @Override
  public void save(UsersOperationList usersOperationList) throws CustomException {
    UsersOperationList usersOperation =
        repository.findByCtnAndName(usersOperationList.getCtn(), usersOperationList.getName());
    if (usersOperation == null) {
      usersOperationList.setDateLastWithdrawal(new Date());
      repository.save(usersOperationList);
    } else {
      throw new CustomException("OPERATION_ALREADY_CREATED", 9);
    }
  }

  @Override
  public void update(UsersOperationList usersOperationList) throws CustomException {
    UsersOperationList operationList =
        repository.findByCtnAndName(usersOperationList.getCtn(), usersOperationList.getName());
    if (operationList != null) {
      operationList.setOperation(usersOperationList.getOperation());
      operationList.setActive(usersOperationList.getActive());
      operationList.setValue(usersOperationList.getValue());
      repository.save(operationList);
    } else {
      throw new CustomException("OPERATION_NOT_FOUND", 10);
    }
  }

  @Override
  public void delete(String ctn, String name) throws CustomException {
    UsersOperationList operationList = findByCtnAndName(ctn, name);
    repository.delete(operationList);
  }

  @Override
  public List<UsersOperationList> findByCtn(String ctn) throws CustomException {
    List<UsersOperationList> lists = repository.findByCtn(ctn);
    if (lists.isEmpty()) {
      throw new CustomException("LIST_IS_EMPTY", 8);
    }
    return lists;
  }

  @Override
  public BigDecimal[] sumValues(String ctn) {
    BigDecimal sumActive = repository.sumValuesByActiveStatusAndCtn(ctn, true);
    BigDecimal sumInactive = repository.sumValuesByActiveStatusAndCtn(ctn, false);

    return new BigDecimal[] {sumActive, sumInactive};
  }

  @Override
  public List<UsersOperationList> findByActive(Boolean active) throws CustomException {
    List<UsersOperationList> operationLists = repository.findByActive(active);
    if (operationLists.isEmpty()) {
      throw new CustomException("LIST_IS_EMPTY", 8);
    }
    return operationLists;
  }
}
