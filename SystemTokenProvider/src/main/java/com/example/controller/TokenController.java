package com.example.controller;

import com.example.exception.CustomException;
import com.example.response.ResponseMessage;
import com.example.response.TokenResponseMessage;
import com.example.service.SystemTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class TokenController {
  private static final RestTemplate restTemplate = new RestTemplate();
  private final SystemTokenService service;

  @Autowired
  public TokenController(SystemTokenService service) {
    this.service = service;
  }

  @GetMapping("/sec/st/checkSystemToken")
  public ResponseEntity<ResponseMessage> checkSystemToken(@RequestParam String systemToken)
      throws CustomException {
    TokenResponseMessage response =
        new TokenResponseMessage("Successes", null, "200", service.findBySystemToken(systemToken));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/core/sr/checkAuthToken")
  public ResponseEntity<ResponseMessage> checkSystemToken(
      @RequestParam(required = false) String ctn, @RequestParam String token)
      throws CustomException {
    //        getUsersBlockList(ctn, token);
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  private void getUsersBlockList(String ctn, String token) throws CustomException {
    try {
      String url = "http://localhost:8080/core/sr/checkAuthToken?ctn=" + ctn + "&token=" + token;
      ResponseEntity<Void> responseEntity = restTemplate.getForEntity(url, Void.class);
      HttpStatus httpStatus = responseEntity.getStatusCode();
    } catch (HttpServerErrorException e) {
      throw new CustomException("TOKEN_ERROR", 500);
    }
  }
}
