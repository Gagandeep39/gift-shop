/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-29 12:51:03
 * @modify date 2021-01-29 12:51:03
 * @desc [description]
 */
package com.cg.authservice.services;

import com.cg.authservice.dto.LoginResponse;
import com.cg.authservice.dto.SocialSignInRequest;
import com.cg.authservice.dto.SocialSignUpRequest;

public interface SocialSignInService {

  LoginResponse signInWithGoogle(SocialSignInRequest request);

  LoginResponse signInWithFacebook(SocialSignInRequest request);

  LoginResponse socialSignUp(SocialSignUpRequest request);

}
