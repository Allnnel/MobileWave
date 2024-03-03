package com.example.repository;

import com.example.model.UsersOperationList;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersOperationListRepository extends JpaRepository<UsersOperationList, Long> {
  UsersOperationList findByCtnAndName(String ctn, String name);

  List<UsersOperationList> findByCtn(String ctn);

  @Query(
      "SELECT COALESCE(SUM(uol.value), 0) FROM UsersOperationList uol WHERE uol.ctn = :ctn AND"
          + " uol.active = :active")
  BigDecimal sumValuesByActiveStatusAndCtn(
      @Param("ctn") String ctn, @Param("active") boolean active);

  List<UsersOperationList> findByActive(Boolean active);
}
