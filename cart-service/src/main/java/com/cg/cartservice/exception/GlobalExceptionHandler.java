package com.cg.cartservice.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	//handle specific exceptions
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException exception,WebRequest request){
		ErrorResponse response =new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(response,HttpStatus.NOT_FOUND);
	}
	
	//handle specific exceptions
		@ExceptionHandler(APIException.class)
		public ResponseEntity<?> handleAPIException(APIException exception,WebRequest request){
			ErrorResponse response =new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity(response,HttpStatus.NOT_FOUND);
		}
	
	
	//handle global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest request){
		ErrorResponse response =new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//
//        List<ErrorResponse> response = new ArrayList<>();
//        ex.getBindingResult()
//            .getAllErrors()
//            .forEach(error -> {
//                response.add(ErrorResponse.builder()
//                    .field(((FieldError) error).getField())
//                    .message(error.getDefaultMessage()).build());
//        });
//
//        return ResponseEntity
//            .status(HttpStatus.BAD_REQUEST)
//            .body(ErrorResponse.builder()
//                .timeStamp(System.currentTimeMillis())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .message("FieldException")
//                .errors(response)
//                .build());
//    }
//	

}
