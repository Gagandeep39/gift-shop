/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-09 05:23:11
 * @modify date 2021-01-09 05:23:11
 * @desc [description]
 */
package com.cg.authservice.services;

import java.util.Map;

import com.cg.authservice.dto.ForgotPasswordRequest;
import com.cg.authservice.dto.LoginRequest;
import com.cg.authservice.dto.LoginResponse;

public interface AuthService {
  LoginResponse login(LoginRequest loginRequest);

  Map<String, String> fetchSecurityQuestionForUser(String username);

  Map<String, String> validateAnswerAndUpdate(ForgotPasswordRequest forgotPasswordRequest);

}
