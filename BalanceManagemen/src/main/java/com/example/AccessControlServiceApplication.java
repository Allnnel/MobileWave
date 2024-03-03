package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccessControlServiceApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AccessControlServiceApplication.class);
    app.run(args);
  }
}
