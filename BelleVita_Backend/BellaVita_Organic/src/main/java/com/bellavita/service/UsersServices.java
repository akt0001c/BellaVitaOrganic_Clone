package com.bellavita.service;

import java.util.List;

import com.bellavita.entity.Address;
import com.bellavita.entity.Users;

public interface UsersServices {
 public String adminSignUp(Users user);
 public String  userSignUp(Users user);
 public Users  getUserDetails(String email);
 public List<Users> getAllUsers();
 public Users changeStatus(String email,String status);
 public String deactivateAccount(String email);
 public Address addAddress(String email, Address ob,String addressType);
 public List<Address>  getAllAddress(String email,String FirstName);
 
 
}
