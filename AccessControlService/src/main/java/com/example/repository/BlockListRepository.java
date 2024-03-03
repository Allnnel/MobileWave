package com.example.repository;

import com.example.model.BlockList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockListRepository extends JpaRepository<BlockList, Long> {
  Optional<BlockList> findByBlockType(String blockType);
}
