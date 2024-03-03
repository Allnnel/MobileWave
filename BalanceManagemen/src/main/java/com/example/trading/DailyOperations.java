package com.example.trading;

import com.example.controller.ExternalServiceController;
import com.example.exception.CustomException;
import com.example.model.UsersOperationList;
import com.example.service.UsersOperationListService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyOperations {
  private final UsersOperationListService usersOperationListService;
  private final Logger logger = Logger.getLogger(DailyOperations.class.getName());

  @Value("${system.token}")
  private String systemToken;

  @Autowired
  public DailyOperations(UsersOperationListService usersOperationListService) {
    this.usersOperationListService = usersOperationListService;
  }

  @Scheduled(cron = "0 0 2 * * *", zone = "Europe/Moscow")
  public void performDailyOperations() {
    try {
      double totalPlus = 0.0;
      double totalMinus = 0.0;
      List<UsersOperationList> operationList = usersOperationListService.findByActive(true);
      for (UsersOperationList operation : operationList) {
        ExternalServiceController.putUserBalance(
            systemToken,
            operation.getCtn(),
            operation.getOperation(),
            operation.getValue(),
            operation.getDescription());

        logger.info("Operation: " + operation.getId() + " Result: " + operation.getOperation());
        if (operation.getOperation().equals("plus")) {
          totalPlus += operation.getValue();
        } else {
          totalMinus -= operation.getValue();
        }
        logger.info("Total plus: " + totalPlus);
        logger.info("Total minus: " + totalMinus);
      }
    } catch (CustomException e) {
      logger.severe("An error occurred during processing daily operations: " + e.getMessage());
    }
  }
}
