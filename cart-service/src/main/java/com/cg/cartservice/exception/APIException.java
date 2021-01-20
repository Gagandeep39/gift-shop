package com.cg.cartservice.exception;

public class APIException extends RuntimeException{
	
	private static final long serialVersionUID=1l;
	
	public APIException(String message) {
		super(message);
	}

}
