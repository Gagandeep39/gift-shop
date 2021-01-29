package com.cg.authservice.services;

import com.cg.authservice.dto.GoogleSignInRequest;
import com.cg.authservice.dto.LoginResponse;

public interface SocialSignInService {

  LoginResponse signInWithGoogle(GoogleSignInRequest request);
  
}
