package com.vitaOrganic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
  
	@GetMapping("/welcome")
	public ResponseEntity<String> getMessage(){
		return new ResponseEntity<>("Welcome without spring security",HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/Secure/welcome")
	public ResponseEntity<String> getSecureMessage(){
		return new ResponseEntity<>("Welcome with spring security",HttpStatus.ACCEPTED);
	}
}
           