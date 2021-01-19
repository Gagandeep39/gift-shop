package com.cg.authservice.exceptions;

public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 5512565657286379434L;

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message) {
    super(message);
  }

}
