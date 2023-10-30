package com.vitaOrganic.execptions;

public class UserNotLoggedInException extends RuntimeException {

	public UserNotLoggedInException() {
		
	}

	public UserNotLoggedInException(String message) {
		super(message);
		
	}

	

}
