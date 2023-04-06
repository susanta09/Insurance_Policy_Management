package com.insurance_policy.API_ExpectionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
	//handle specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleReSourceNotFoundException( ResourceNotFoundException ex,WebRequest request)
	{
	  ErrorDetails errorDetails=new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	  return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex)
	{
		Map<String, String> errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			errors.put(((FieldError) error).getField(),error.getDefaultMessage());
		});
		return errors;
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException( Exception ex,WebRequest request)
	{
	  ErrorDetails errorDetails=new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	  return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}



}
