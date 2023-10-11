package com.vitaOrganic.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vitaOrganic.entity.Address;
import com.vitaOrganic.entity.Users;
import com.vitaOrganic.services.UsersServices;

@Service
public class UserServicesImpl implements UsersServices {

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
		// TODO Auto-generated method stub
		List<Users>  list = new ArrayList<>();
		return list;
	}

	@Override
	public Users changeStatus(String email ,String status) {
		// TODO Auto-generated method stub
		return new Users();
	}

	@Override
	public String deactivateAccount(String email) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List<Address> getAllAddress(String email, String FirstName) {
		// TODO Auto-generated method stub
		List<Address>  addressList =  new ArrayList<>();
		return addressList;
	}

}
