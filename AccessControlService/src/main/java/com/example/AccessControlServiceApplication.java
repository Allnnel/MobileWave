package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccessControlServiceApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AccessControlServiceApplication.class);
    app.run(args);
  }

}
