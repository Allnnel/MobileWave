package com.example.controller;

import com.example.exception.CustomException;
import com.example.model.UsersOperationHistory;
import com.example.model.UsersOperationList;
import com.example.response.HistoryResponseMessage;
import com.example.response.ListResponseMessage;
import com.example.response.ResponseMessage;
import com.example.service.UsersBalanceServiceImpl;
import com.example.service.UsersOperationHistoryServiceImpl;
import com.example.service.UsersOperationListServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperationController {

  private final UsersOperationHistoryServiceImpl historyService;
  private final UsersOperationListServiceImpl listService;
  private final UsersBalanceServiceImpl balanceService;

  @Autowired
  public OperationController(
      UsersOperationHistoryServiceImpl historyService,
      UsersOperationListServiceImpl listService,
      UsersBalanceServiceImpl balanceService) {
    this.historyService = historyService;
    this.listService = listService;
    this.balanceService = balanceService;
  }

  @GetMapping("/balance/userOperationHistory")
  public ResponseEntity<ResponseMessage> getOperationHistory(
      @RequestParam String systemToken, @RequestParam String ctn) throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    List<UsersOperationHistory> historyList = historyService.findByCtn(ctn);
    HistoryResponseMessage response =
        new HistoryResponseMessage("Successes", null, "200", historyList);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/balance/userOperation")
  public ResponseEntity<ResponseMessage> getUserOperation(
      @RequestParam String systemToken, @RequestParam String ctn, @RequestParam String name)
      throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    UsersOperationList lists = listService.findByCtnAndName(ctn, name);
    ListResponseMessage response = new ListResponseMessage("Successes", null, "200", lists);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/balance/userOperation")
  public ResponseEntity<ResponseMessage> postUserOperation(
      @RequestParam String systemToken, @RequestBody UsersOperationList usersOperationList)
      throws CustomException {
    ExternalServiceController.checkSystemToken(usersOperationList.getCtn(), systemToken);
    balanceService.isValidOperation(usersOperationList.getOperation());
    listService.save(usersOperationList);
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/balance/userOperation")
  public ResponseEntity<ResponseMessage> putUserOperation(
      @RequestParam String systemToken, @RequestBody UsersOperationList usersOperationList)
      throws CustomException {
    ExternalServiceController.checkSystemToken(usersOperationList.getCtn(), systemToken);
    balanceService.isValidOperation(usersOperationList.getOperation());
    listService.update(usersOperationList);
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @DeleteMapping("/balance/userOperation")
  public ResponseEntity<ResponseMessage> deleteUserOperation(
      @RequestParam String systemToken, @RequestParam String name, @RequestParam String ctn)
      throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    listService.delete(ctn, name);
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/balance/userOperationList")
  public ResponseEntity<ResponseMessage> getUserOperationList(
      @RequestParam String systemToken, @RequestParam String ctn) throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    List<UsersOperationList> lists = listService.findByCtn(ctn);
    ListResponseMessage response = new ListResponseMessage("Successes", null, "200", lists);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/balance/userOperationListSum")
  public ResponseEntity<ResponseMessage> getUserOperationListSum(
      @RequestParam String systemToken, @RequestParam String ctn) throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    BigDecimal[] bigDecimals = listService.sumValues(ctn);
    ListResponseMessage response =
        new ListResponseMessage("Successes", null, "200", bigDecimals[0], bigDecimals[1]);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
