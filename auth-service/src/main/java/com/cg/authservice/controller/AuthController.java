/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-09 05:22:02
 * @modify date 2021-01-09 05:22:02
 * @desc [description]
 */
package com.cg.authservice.controller;

import java.util.Map;

import javax.validation.Valid;

import com.cg.authservice.dto.ForgotPasswordRequest;
import com.cg.authservice.dto.LoginRequest;
import com.cg.authservice.dto.LoginResponse;
import com.cg.authservice.services.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
  }
  
  @GetMapping("/forgotpassword/{username}")
  public ResponseEntity<Map<String, String>> forgotPassword(@PathVariable String username) {
    return ResponseEntity.status(HttpStatus.OK).body(authService.fetchSecurityQuestionForUser(username));
  }

  @PutMapping("/forgotpassword")
  public ResponseEntity<Map<String, String>> validateAnswerAndUpdatePassword(
      @Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(authService.validateAnswerAndUpdate(forgotPasswordRequest));
  }

}
