package com.example.controller;

import com.example.exception.CustomException;
import com.example.model.сontrolService.UsersBlock;
import com.example.response.controlService.ResponseMessageBlockList;
import java.util.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class ExternalServiceController {

  private static final RestTemplate restTemplate = new RestTemplate();

  public static void checkSystemToken(String ctn, String systemToken) throws CustomException {

    try {
      String url = "http://localhost:8081/sec/st/checkSystemToken?" + "systemToken=" + systemToken;
      restTemplate.exchange(url, HttpMethod.GET, null, Void.class);
    } catch (HttpServerErrorException e) {
      throw new CustomException("TOKEN_ERROR", 1);
    }
  }

  public static Optional<List<UsersBlock>> usersCheckBlock(
      String ctn, String systemToken, String operation) throws CustomException {
    try {
      String url = "http://localhost:8080/sec/checkBlock?" + "ctn=" + ctn + "&token=" + systemToken;
      ResponseEntity<ResponseMessageBlockList> responseEntity =
          restTemplate.exchange(url, HttpMethod.GET, null, ResponseMessageBlockList.class);
      ResponseMessageBlockList response = responseEntity.getBody();
      if (response != null) {
        List<UsersBlock> usersBlockList = response.getBlockList();
        if (usersBlockList != null) {
          for (UsersBlock usersBlock : usersBlockList)
            if (Objects.equals(usersBlock.getBlockType(), "finBlock") && operation.equals("minus"))
              throw new CustomException("CLIENT_IS_BLOCKED", 1);
        }
        return Optional.ofNullable(usersBlockList);
      }
    } catch (HttpServerErrorException e) {
      return Optional.empty();
    }
    return Optional.empty();
  }

  public static void usersBlock(
      String ctn, String systemToken, String blockType, String description) throws CustomException {
    try {
      String url =
          "http://localhost:8080/sec/block?"
              + "ctn="
              + ctn
              + "&blockType="
              + blockType
              + "&token="
              + systemToken
              + "&description="
              + description;
      restTemplate.exchange(url, HttpMethod.POST, null, Void.class);
    } catch (HttpServerErrorException e) {
      throw new CustomException("TOKEN_ERROR", 1);
    }
  }

  public static void deleteUsersBlock(String ctn, String systemToken, String blockType)
      throws CustomException {
    try {
      String url =
          "http://localhost:8080/sec/block?"
              + "ctn="
              + ctn
              + "&blockType="
              + blockType
              + "&token="
              + systemToken;
      restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    } catch (HttpServerErrorException e) {
      throw new CustomException("TOKEN_ERROR", 1);
    }
  }

  public static void putUserBalance(
      String systemToken, String ctn, String operation, double value, String description)
      throws CustomException {
    try {
      String url =
          "http://localhost:8082/balance/userBalance?"
              + "systemToken="
              + systemToken
              + "&ctn="
              + ctn
              + "&operation="
              + operation
              + "&value="
              + value
              + "&description="
              + description;
      restTemplate.exchange(url, HttpMethod.PUT, null, Void.class);
    } catch (HttpServerErrorException e) {
      throw new CustomException("TOKEN_ERROR", 1);
    }
  }
}
