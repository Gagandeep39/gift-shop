/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-29 11:47:56
 * @modify date 2021-01-29 11:47:56
 * @desc [description]
 */
package com.cg.authservice.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.cg.authservice.dto.GoogleSignInRequest;
import com.cg.authservice.dto.LoginResponse;
import com.cg.authservice.services.SocialSignInService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/social")
@RestController
@AllArgsConstructor
public class SocialSignInController {

  private SocialSignInService signInService;

  @PostMapping("/google")
  public ResponseEntity<LoginResponse> googleSignIn(@RequestBody GoogleSignInRequest request)
      throws GeneralSecurityException, IOException {
    return ResponseEntity.status(HttpStatus.OK).body(signInService.signInWithGoogle(request));
  }

}
