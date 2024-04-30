package com.example.controller;

import com.example.exception.CustomException;
import com.example.model.UsersBlock;
import com.example.response.ResponseMessage;
import com.example.response.UsersBlockResponseMessage;
import com.example.service.UsersBlockService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class BlockController {

  private final UsersBlockService usersBlockService;

  private final RestTemplate restTemplate;

  @Autowired
  public BlockController(UsersBlockService usersBlockService, RestTemplate restTemplate) {
    this.usersBlockService = usersBlockService;
    this.restTemplate = restTemplate;
  }

  @GetMapping("/sec/checkBlock")
  public ResponseEntity<UsersBlockResponseMessage> checkBlock(
      @RequestParam String ctn, @RequestParam String token) throws CustomException {
    getUsersBlockList(ctn, token);
    List<UsersBlock> usersBlockList = usersBlockService.findByCtn(ctn);
    UsersBlockResponseMessage response =
        new UsersBlockResponseMessage("Successes", null, "200", true, usersBlockList);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/sec/block")
  public ResponseEntity<ResponseMessage> postCheckBlock(
      @RequestParam String ctn,
      @RequestParam String token,
      @RequestParam String blockType,
      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
      @RequestParam(required = false) String description)
      throws CustomException {
    getUsersBlockList(ctn, token);
    try {
      usersBlockService.findByCtnAndBlockType(blockType, ctn);
      throw new CustomException("CTN_ALREADY_BLOCKED", 3);
    } catch (CustomException e) {
      UsersBlock usersBlock = new UsersBlock(ctn, blockType, new Date(), endDate, description);
      usersBlockService.save(usersBlock);
      ResponseMessage response = new ResponseMessage("Successes", null, "200");
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }
  }

  @PutMapping("/sec/block")
  public ResponseEntity<ResponseMessage> putCheckBlock(
      @RequestParam String ctn,
      @RequestParam String token,
      @RequestParam String blockType,
      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
      @RequestParam(required = false) String description)
      throws CustomException {

    getUsersBlockList(ctn, token);
    UsersBlock usersBlock = new UsersBlock(ctn, blockType, new Date(), endDate, description);
    usersBlockService.update(usersBlock);
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/sec/block")
  public ResponseEntity<ResponseMessage> deleteCheckBlock(
      @RequestParam String ctn, @RequestParam String blockType, @RequestParam String token)
      throws CustomException {
    getUsersBlockList(ctn, token);
    usersBlockService.deleteByCtnAndBlockType(ctn, blockType);
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  private void getUsersBlockList(String ctn, String token) throws CustomException {
    try {
      String url = "http://localhost:8081//sec/st/checkSystemToken?" + "systemToken=" + token;
      restTemplate.getForEntity(url, Void.class);
    } catch (HttpServerErrorException e) {
      throw new CustomException("TOKEN_NOT_FOUND", 500);
    }
  }
}
