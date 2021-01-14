/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-14 11:54:02
 * @modify date 2021-01-14 11:54:02
 * @desc [description]
 */
package com.cg.authservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HelloWorld {

  @GetMapping
  public String helloWorld() {
    return "Hello World";
  }
  
}
