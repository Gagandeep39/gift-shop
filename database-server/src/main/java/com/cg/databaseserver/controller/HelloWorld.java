/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 16:40:39
 * @modify date 2021-01-13 16:40:39
 * @desc [description]
 */
package com.cg.databaseserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

  @Value("${server.port}")
  private String serverPort;
  @Value("${spring.application.name}")
  private String applicationName;

  @GetMapping
  public String helloWorld() {
    return "Hello from " + applicationName + " running at " + serverPort;
  }

}
