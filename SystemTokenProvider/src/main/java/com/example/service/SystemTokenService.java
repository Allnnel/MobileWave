package com.example.service;

import com.example.exception.CustomException;
import com.example.model.SystemToken;

public interface SystemTokenService {
  SystemToken findBySystemToken(String systemTokenName) throws CustomException;
}
