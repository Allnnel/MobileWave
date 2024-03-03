package com.example.service;

import com.example.exception.CustomException;
import com.example.model.SystemToken;
import com.example.repository.SystemTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemTokenServiceImpl implements SystemTokenService {
    private final SystemTokenRepository systemTokenRepository;

    @Autowired
    public SystemTokenServiceImpl(SystemTokenRepository systemTokenRepository) {
        this.systemTokenRepository = systemTokenRepository;
    }

    @Override
    public SystemToken findBySystemToken(String systemTokenName) throws CustomException {
        Optional<SystemToken> systemToken = systemTokenRepository.findBySystemToken(systemTokenName);
        if (!systemToken.isPresent()) {
            throw new CustomException("TOKEN_NOT_FOUND", 1);
        }
        return systemToken.get();
    }

}
