package com.vitaOrganic.services;

import java.util.List;

import com.vitaOrganic.entity.Address;
import com.vitaOrganic.entity.Users;

public interface UsersServices {
 public String logIn(String email,String password);
 public Users  signUp(Users user);
 public Users  getUserDetails(String email);
 public List<Users> getAllUsers();
 public Users changeStatus(String email);
 public String deactivateAccount(String email);
 public List<Address>  getAllAddress(String email,String FirstName,String LastName);
 
 
}
