package com.example.repository;

import com.example.model.SystemToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemTokenRepository extends JpaRepository<SystemToken, Long> {
    Optional<SystemToken> findBySystemToken(String systemToken);
}
