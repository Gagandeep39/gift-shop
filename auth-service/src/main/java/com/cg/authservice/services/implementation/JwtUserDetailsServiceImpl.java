/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-14 11:57:23
 * @modify date 2021-01-14 11:57:23
 * @desc [description]
 */
package com.cg.authservice.services.implementation;

import java.util.ArrayList;

import com.cg.authservice.entities.User;
import com.cg.authservice.exceptions.InvalidCredentialException;
import com.cg.authservice.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository
      .findByUsername(username)
      .orElseThrow(() -> new InvalidCredentialException("username", "User " + username + " doesn't exist"));
    return new org.springframework.security.core.userdetails.User(
      user.getUsername(), 
      user.getPassword(), 
      new ArrayList<>()
    );
  }
  
}
