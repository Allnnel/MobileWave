package com.example.repository;

import com.example.model.UsersOperationHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersOperationHistoryRepository
    extends JpaRepository<UsersOperationHistory, Long> {
  List<UsersOperationHistory> findByCtn(String cnt);
}
