package com.example.service;

import com.example.exception.CustomException;
import com.example.model.UsersOperationHistory;
import java.util.List;

public interface UsersOperationHistoryService {
  void save(UsersOperationHistory history);

  List<UsersOperationHistory> findByCtn(String ctn) throws CustomException;
}
