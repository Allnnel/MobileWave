package com.example.repository;

import com.example.model.UsersBlock;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersBlockRepository extends JpaRepository<UsersBlock, Long> {
  List<UsersBlock> findByCtn(String ctn);

  List<UsersBlock> findByBlockType(String blockType);

  Optional<UsersBlock> findByCtnAndBlockType(String ctn, String blockType);

  void deleteByCtn(String ctn);

  void deleteByCtnAndBlockType(String ctn, String blockType);
}
