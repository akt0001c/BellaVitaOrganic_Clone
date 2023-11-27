package com.bellavita.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(OperationFaliureException.class)
	public ResponseEntity<MyErrorDetails>  operationFaliureExceptionHandler(OperationFaliureException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<MyErrorDetails>  orderNotFoundExceptionHandler(OrderNotFoundException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails>  orderExceptionHandler(OrderException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
		
		
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails>  productExceptionHandler(ProductException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MyErrorDetails>  productNotFoundExceptionHandler(ProductNotFoundException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(TransactionMethodNotFoundException.class)
	public ResponseEntity<MyErrorDetails>  transactionMethodNotFoundExceptionHandler(TransactionMethodNotFoundException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<MyErrorDetails>  transactionNotFoundExceptionHandler(TransactionNotFoundException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotLoggedInException.class)
	public ResponseEntity<MyErrorDetails>  userNotLoggedInExceptionHandler(UserNotLoggedInException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<MyErrorDetails>  userAlreadyExistExceptionHandler(UserAlreadyExistException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAccountInactiveException.class)
	public ResponseEntity<MyErrorDetails>  userAccountInactiveExceptionHandler(UserAccountInactiveException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MyErrorDetails>  userNotFoundExceptionHandler(UserNotFoundException exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails>  noValidArgumentExceptionHandler(MethodArgumentNotValidException exp){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getLocalizedMessage());
		err.setDetails(exp.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails>  myExceptionHandler(Exception exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails>  noHandlerFoundExceptionHandler(Exception exp,WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	

}
