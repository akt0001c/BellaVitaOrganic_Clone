package com.bellavita.exceptions;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException() {
		//This is default constructor
	}

	public OrderNotFoundException(String message) {
		super(message);
		//This is parameterized constructor with massage  as parameter
	}

	
}
