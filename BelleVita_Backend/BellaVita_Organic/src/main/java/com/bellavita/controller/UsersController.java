package com.bellavita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bellavita.entity.Address;
import com.bellavita.entity.Users;
import com.bellavita.exceptions.OperationFaliureException;
import com.bellavita.exceptions.UserNotLoggedInException;
import com.bellavita.service.UsersServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
	
private UsersServices uservice;
private PasswordEncoder pencoder;


	@Autowired
    public void setPencoder(PasswordEncoder pencoder) {
	this.pencoder = pencoder;
}


	@Autowired
	public void setUservice(UsersServices uservice) {
	this.uservice = uservice;
}


	@PostMapping("/signUp")
	public ResponseEntity<Users> signUp(@Valid @RequestBody  Users user){
		user.setPassword(pencoder.encode(user.getPassword()));
		
		Users res=null;
		if(user.getRole().equalsIgnoreCase("user"))
			res= uservice.userSignUp(user);
		else if(user.getRole().equalsIgnoreCase("admin"))
			 res= uservice.adminSignUp(user);
		else
			throw new OperationFaliureException("Some thing went wrong ,Please try again");
		
	    return new ResponseEntity<>(res,HttpStatus.CREATED);	
	}
	
	@GetMapping("/signIn")
	public ResponseEntity<String> getLoginDetails(Authentication auth){
		System.out.println("welcome login in progress");
	    System.out.println("authentication in progress for :"+auth.getName());
		
		Users user= uservice.getUserDetails(auth.getName());
		String res= user.getFirstName()+" "+user.getLastName()+" "+"Logged in Successfully";
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
			
	}
	
	
    @GetMapping("/testing")
    public ResponseEntity<String> testMethod(){
    	return new ResponseEntity<>("Welcome User",HttpStatus.ACCEPTED);
    }
    
	@GetMapping("/logged/user")
	public ResponseEntity<Users> getUser(Authentication auth){
		if(auth ==null)
			  throw new UserNotLoggedInException("Sorry you are not log in,Please login first");
		
		Users user= uservice.getUserDetails(auth.getName());
	
		return new ResponseEntity<>(user ,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/{uemail}")
	public ResponseEntity<Users> getUserdetails(@PathVariable("uemail") String email){
		
		Users user= uservice.getUserDetails(email);
	
		return new ResponseEntity<>(user ,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUser(){
		List<Users> list = uservice.getAllUsers();
		
	
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	} 
	
	@PatchMapping("/user/status")
	public ResponseEntity<Users> changeStatus(@RequestParam("uemail") String email, @RequestParam("ustatus") String status){
		Users user= uservice.changeStatus(email, status);
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
	
//	@PatchMapping("/user/logged/deactivate")
//	public ResponseEntity<String> deactivateAccountByUser(Authentication auth)
//	{
//		if(auth.getName()==null)
//			  throw new UserNotLoggedInException("Sorry you are not log in,Please login first");
//		
//		String res= uservice.deactivateAccount(auth.getName());
//		
//		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
//	}
	
	@GetMapping("/addresses")
	public ResponseEntity<List<Address>> getAddresses(@RequestParam("ueamil") String ueamil,@RequestParam("fname") String fname){
		List<Address> list = uservice.getAllAddress(ueamil,fname);
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/add/address")
	public ResponseEntity<Address> addAdress(@Valid  Authentication auth,@RequestBody  Address address,@RequestParam("type") String addressType){
		String uemail= auth.getName();
		if(uemail==null)
			 throw new UserNotLoggedInException("You are not logged in ,Please login first");
		
		Address res= uservice.addAddress(uemail, address, addressType);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	
	
	
}
