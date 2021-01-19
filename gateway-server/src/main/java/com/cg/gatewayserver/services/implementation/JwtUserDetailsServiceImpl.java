/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-19 22:56:04
 * @modify date 2021-01-19 22:56:04
 * @desc Spring security user detials 
 */
package com.cg.gatewayserver.services.implementation;

import java.util.ArrayList;

import com.cg.gatewayserver.entities.User;
import com.cg.gatewayserver.exceptions.InvalidCredentialException;
import com.cg.gatewayserver.repositories.UserRepository;

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
