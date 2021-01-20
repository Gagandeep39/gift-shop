package com.cg.cartservice.exception;

public class CustomException extends RuntimeException{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomException [message=" + message + "]";
	}

	public CustomException(String message) {
		super();
		this.message = message;
	}
	
	
	
	
}
