package com.project.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<Object> handleException(Exception exception, WebRequest request){
		Error error = new Error();
		error.setTimestamp(new Date());
		error.setStatus(404);
		error.setError(exception.getMessage());
		error.setPath(request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler({UserAlreadyExistsException.class, ProjectAlreadyExists.class})
	public ResponseEntity<Object> handleCustomException(Exception exception, WebRequest request){
		Error error = new Error();
		error.setTimestamp(new Date());
		error.setStatus(400);
		error.setError(exception.getMessage());
		error.setPath(request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	
	//this exception is thrown when validation on an argument annotated with @Valid fails
	@ExceptionHandler(MethodArgumentNotValidException.class)	
	public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception, WebRequest request){
		Error error= new Error();
				error.setTimestamp(new Date());
				error.setStatus(400);
				error.setError(exception.getMessage());
				error.setPath(request.getDescription(false));
				exception.getBindingResult().
				getFieldError().
				getDefaultMessage();
		 
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
