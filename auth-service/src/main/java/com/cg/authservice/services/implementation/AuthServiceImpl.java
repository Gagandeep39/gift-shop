/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-14 11:56:57
 * @modify date 2021-01-14 11:56:57
 * @desc [description]
 */
package com.cg.authservice.services.implementation;

import com.cg.authservice.dto.LoginRequest;
import com.cg.authservice.dto.LoginResponse;
import com.cg.authservice.entities.User;
import com.cg.authservice.exceptions.InvalidCredentialException;
import com.cg.authservice.repositories.UserRepository;
import com.cg.authservice.security.JwtProvider;
import com.cg.authservice.services.AuthService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;


  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    User user = findUserByCredentials(loginRequest.getUsername(), loginRequest.getPassword());
    return LoginResponse.builder()
      .userId(user.getUserId())
      .username(user.getUsername())
      .role(user.getRole())
      .token(jwtProvider.generateWithUsernameAndId(user.getUsername(), user.getUserId()))
      .build();
  } 

  @Transactional(readOnly = true)
  public User findUserByCredentials(String username, String password) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new InvalidCredentialException("username", "User " + username + " doesn't exist"));
    if (!passwordEncoder.matches(password, user.getPassword())) throw new InvalidCredentialException("password", "Invalid Password");
    return user;
  }
  
}
