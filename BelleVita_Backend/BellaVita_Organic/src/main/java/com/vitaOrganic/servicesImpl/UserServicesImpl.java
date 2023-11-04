package com.vitaOrganic.servicesImpl;

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
	public String logIn(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String signUp(Users user) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public Users getUserDetails(String email) {
		// TODO Auto-generated method stub
		return new Users();
	}

	@Override
	public List<Users> getAllUsers() {
		
		List<Users>  res = urepo.findAll();
		return res;
	}

	@Override
	public Users changeStatus(String email ,String status) {
		 Users user= urepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User does not exist with this email : "+email));
		 try {
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

}
