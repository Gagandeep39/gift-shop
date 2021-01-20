package com.cg.cartservice.exception;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ErrorResponse {


	private Date timestamp;
	private String message;
	private String details;
	//private List<FieldErrorResponse> errors;
	
//	public ErrorResponse(Date timestamp, String message, String details, List<FieldErrorResponse> errors) {
//		super();
//		this.timestamp = timestamp;
//		this.message = message;
//		this.details = details;
//		this.errors = errors;
//	}

	public ErrorResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	 
}
