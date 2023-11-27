package com.bellavita.exceptions;

public class UserAccountInactiveException extends RuntimeException {

	  public UserAccountInactiveException() {}
	  
	  public UserAccountInactiveException(String msg) {
		  super(msg);
	  }
}
