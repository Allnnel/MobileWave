package com.example.service.impl;

import com.example.exception.CustomException;
import com.example.model.UsersOperationHistory;
import com.example.repository.UsersOperationHistoryRepository;
import com.example.service.UsersOperationHistoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersOperationHistoryServiceImpl implements UsersOperationHistoryService {

  private final UsersOperationHistoryRepository repository;

  @Autowired
  public UsersOperationHistoryServiceImpl(UsersOperationHistoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(UsersOperationHistory history) {
    repository.save(history);
  }

  @Override
  public List<UsersOperationHistory> findByCtn(String ctn) throws CustomException {
    List<UsersOperationHistory> histories = repository.findByCtn(ctn);
    if (histories.isEmpty()) {
      throw new CustomException("HISTORY_IS_EMPTY", 7);
    }
    return histories;
  }
}
