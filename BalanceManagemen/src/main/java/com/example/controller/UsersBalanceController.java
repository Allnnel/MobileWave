package com.example.controller;

import com.example.exception.CustomException;
import com.example.model.UsersBalance;
import com.example.model.UsersOperationHistory;
import com.example.model.сontrolService.UsersBlock;
import com.example.response.ResponseMessage;
import com.example.response.UserResponseMessage;
import com.example.service.UsersBalanceService;
import com.example.service.UsersOperationHistoryService;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersBalanceController {

  private final UsersBalanceService usersBalanceService;
  private final UsersOperationHistoryService usersOperationHistoryService;

  @Autowired
  public UsersBalanceController(
      UsersBalanceService usersBalanceService,
      UsersOperationHistoryService usersOperationHistoryService) {
    this.usersBalanceService = usersBalanceService;
    this.usersOperationHistoryService = usersOperationHistoryService;
  }

  @GetMapping("/balance/userBalance")
  public ResponseEntity<ResponseMessage> getUserBalance(
      @RequestParam String systemToken, @RequestParam String ctn) throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    UsersBalance balance = usersBalanceService.findByCtn(ctn);
    ResponseMessage response =
        new UserResponseMessage("Successes", null, "200", balance.getBalance());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/balance/userBalance")
  public ResponseEntity<ResponseMessage> postUserBalance(
      @RequestParam String systemToken, @RequestParam String ctn) throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    usersBalanceService.save(ctn, 0.0);
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/balance/userBalance")
  public ResponseEntity<ResponseMessage> putUserBalance(
      @RequestParam String systemToken,
      @RequestParam String ctn,
      @RequestParam String operation,
      @RequestParam double value,
      @RequestParam(required = false) String description)
      throws CustomException {
    ExternalServiceController.checkSystemToken(ctn, systemToken);
    usersBalanceService.isValidOperation(operation);
    usersBalanceService.findByCtn(ctn);
    Optional<List<UsersBlock>> usersBlockList =
        ExternalServiceController.usersCheckBlock(ctn, systemToken, operation);
    try {
      usersBalanceService.update(ctn, value, operation);

      if (usersBlockList.isPresent()) {
        List<UsersBlock> blocks = usersBlockList.get();
        for (UsersBlock usersBlock : blocks) {
          if (Objects.equals(usersBlock.getBlockType(), "finBlock"))
            ExternalServiceController.deleteUsersBlock(ctn, systemToken, "finBlock");
        }
      }

    } catch (CustomException e) {
      ExternalServiceController.usersBlock(
          ctn,
          systemToken,
          "finBlock",
          "Блокировка системой балансов пользователей в связи с отрицательным значением баланса.");
    }
    usersOperationHistoryService.save(
        new UsersOperationHistory(ctn, operation, value, description, new Date()));
    ResponseMessage response = new ResponseMessage("Successes", null, "200");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
