package com.vitaOrganic.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitaOrganic.entity.Address;
import com.vitaOrganic.entity.UserStatus;
import com.vitaOrganic.entity.Users;
import com.vitaOrganic.execptions.OperationFaliureException;
import com.vitaOrganic.execptions.UserNotFoundException;
import com.vitaOrganic.execptions.UserNotLoggedInException;
import com.vitaOrganic.repository.UsersRepository;
import com.vitaOrganic.services.UsersServices;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class UserServicesImpl implements UsersServices {
	
	private UsersRepository urepo;

	@Autowired
	public void setUrepo(UsersRepository urepo) {
		this.urepo = urepo;
	}

	

	@Override
	public String userSignUp(Users user) {
		String res=null;
		user.setCreatedAt(LocalDateTime.now());
		user.setRole("USER");
		user.setStatus(UserStatus.Active);
		try {
		
		Users ob= urepo.save(user);
		if(ob!=null)
			 res="User created successfully";
		else
			throw new Exception();
		}catch(Exception e) {
			throw new OperationFaliureException("Something went wrong ,or Operation faliure");
		}
			
		return res;
	}

	@Override
	public Users getUserDetails(String email) {
		 Users user= urepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User does not exist with this email : "+email));
		 
		return  user;
	}

	@Override
	public List<Users> getAllUsers() {
		
		List<Users>  res = urepo.findAll();
		if(res.isEmpty())
			  throw new OperationFaliureException("NO user found");
		
		return res;
	}

	@Override
	public Users changeStatus(String email ,String status) {
		  Users user=null;
		  try {
		   user= urepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User does not exist with this email : "+email));
		 
		 user.setStatus(UserStatus.valueOf(UserStatus.class, status));
		 }catch(IllegalArgumentException | NullPointerException exp) {
			throw new  OperationFaliureException("Operation Faliure due to invalid argument in method ");
		 }
		return user;
	}

	@Override
	public String deactivateAccount(String email) {
		String res=null;
		Users user= urepo.findByEmail(email).orElseThrow(()-> new UserNotLoggedInException("User not logged in,please login first"));
		user.setStatus(UserStatus.Deactivated_by_user);
		 
		if(user.getStatus()== UserStatus.Deactivated_by_user)
			   res= "User account has been deactivated";
		return res;
	}

	@Override
	public List<Address> getAllAddress(String email, String FirstName) {
		
		List<Address>  addressList = null;
		Users user= urepo.findByEmailAndFirstName(email, FirstName).orElseThrow( ()-> new  UserNotFoundException("User does not exist with this email : "+email));
		addressList= user.getAddresses().stream().toList();
		return addressList;
	}



	@Override
	public String adminSignUp(Users user) {
		String res=null;
		user.setCreatedAt(LocalDateTime.now());
		user.setRole("ADMIN");
		user.setStatus(UserStatus.Active);
		try {
		
		Users ob= urepo.save(user);
		if(ob!=null)
			 res="User created successfully";
		else
			throw new Exception();
		}catch(Exception e) {
			throw new OperationFaliureException("Something went wrong ,or Operation faliure");
		}
			
		return res;
	
	}



	@Override
	public Address addAddress(String email, Address ob) {
		
		Users user= urepo.findByEmail(email).orElseThrow( ()-> new  UserNotFoundException("User does not exist with this email : "+email));
		user.getAddresses().add(ob);
		urepo.save(user);
		return ob;
	}

}
