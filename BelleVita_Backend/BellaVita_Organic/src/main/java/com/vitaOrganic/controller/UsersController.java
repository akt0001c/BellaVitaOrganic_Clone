package com.vitaOrganic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vitaOrganic.dto.UserInfoDto;
import com.vitaOrganic.entity.Address;
import com.vitaOrganic.entity.Users;
import com.vitaOrganic.services.UsersServices;

import jakarta.validation.Valid;

@RestController
public class UsersController {
	
private UsersServices uservice;



    @Autowired
	public void setUservice(UsersServices uservice) {
	this.uservice = uservice;
}

//
//	@GetMapping("/signIn")
//	public ResponseEntity<String> logIn(String email,String password){
//		return new ResponseEntity<>("",HttpStatus.ACCEPTED);
//	}
//	
//	
//	@PostMapping("/signUp")
//	public ResponseEntity<String> signUp(Users user){
//	    return new ResponseEntity<>("",HttpStatus.CREATED);	
//	}
	
	@GetMapping("/getUserDetails")
	public ResponseEntity<Users> getUser(Authentication auth){
		
		Users user= uservice.getUserDetails(auth.getName());
		return new ResponseEntity<>(user ,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getAllUser(){
		List<Users> list = uservice.getAllUsers();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	} 
	
	@PatchMapping("/changeUserStatus")
	public ResponseEntity<Users> changeStatus(String email,String status){
		Users user= uservice.changeStatus(email, status);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PatchMapping("/deactivateAccount")
	public ResponseEntity<String> deactivateAccountByUser(Authentication auth)
	{
		String res= uservice.deactivateAccount(auth.getName());
		
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/addresses")
	public ResponseEntity<List<Address>> getAddresses(@Valid @RequestBody UserInfoDto ob){
		List<Address> list = uservice.getAllAddress(ob.getEmail(), ob.getFirstName());
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}
	
	
	
	
}
