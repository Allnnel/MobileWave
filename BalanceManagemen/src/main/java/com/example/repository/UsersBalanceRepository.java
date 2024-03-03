package com.example.repository;

import com.example.model.UsersBalance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersBalanceRepository extends JpaRepository<UsersBalance, Long> {
  Optional<UsersBalance> findByCtn(String ctn);
}
