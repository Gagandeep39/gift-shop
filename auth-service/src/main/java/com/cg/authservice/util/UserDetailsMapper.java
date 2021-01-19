package com.cg.authservice.util;

import com.cg.authservice.dto.RegisterRequest;
import com.cg.authservice.dto.UpdateRequest;
import com.cg.authservice.dto.UserDetailsDto;
import com.cg.authservice.entities.User;
import com.cg.authservice.entities.UserDetails;

public class UserDetailsMapper {

  public static UserDetails registerToUserDetails(RegisterRequest registerRequest) {
    User user = User.builder()
      .username(registerRequest.getUsername())
      .password(registerRequest.getPassword())
      .role("User")
      .build();
    return UserDetails.builder()
      .user(user)
      .address(registerRequest.getAddress())
      .firstName(registerRequest.getFirstName())
      .lastName(registerRequest.getLastName())
      .securityQuestion("What is the default answer?")
      .securityAnswer("answer")
      // .dob(registerRequest.getDob())
      .emailId(registerRequest.getEmailId())
      .phoneNo(registerRequest.getPhoneNo())
      .build();
  }

  public static UserDetailsDto userDetailsToDto(UserDetails userDetails){
    return UserDetailsDto.builder()
      .userId(userDetails.getUserDetailsId())
      .username(userDetails.getUser().getUsername())
      .role(userDetails.getUser().getRole())
      .phoneNo(userDetails.getPhoneNo())
      // .dob(userDetails.getDob())
      .emailId(userDetails.getEmailId())
      .address(userDetails.getAddress())
      .firstName(userDetails.getFirstName())
      .lastName(userDetails.getLastName())
      .build();
  }

  public static UserDetails updateRequestToUserDetails(UpdateRequest updateRequest) {
    return UserDetails.builder()
      .firstName(updateRequest.getFirstName())
      .lastName(updateRequest.getLastName())
      .address(updateRequest.getAddress())
      .userDetailsId(updateRequest.getUserId())
      .phoneNo(updateRequest.getPhoneNo())
      // .gender(Gender.valueOf(updateRequest.getGender()))
      .emailId(updateRequest.getEmailId())
      .securityQuestion(updateRequest.getSecurityQuestion())
      .securityAnswer(updateRequest.getSecurityAnswer())
      .build();
  }

}
