package com.bellavita.service;

import java.util.List;

import com.bellavita.entity.Address;
import com.bellavita.entity.Users;
import com.bellavita.exceptions.OperationFaliureException;
import com.bellavita.exceptions.UserNotFoundException;


public interface UsersServices {
 
	
/**
 * This method is used to create an account for an admin only
 * @author Ankit Choubey
 * @exception OperationFaliureException
 * @param Users  user
 * @return Users 
 */	
 public Users adminSignUp(Users user);

 
 
 /**
 * It used by user to create an account as a user or sign up
 * @exception OperationFaliureException 
 * @param Users object which will be provided by user
 * @author Ankit Choubey
 * @return Users object
 */
 public Users  userSignUp(Users user);
 
 
 /**
 * This mehtod is user to get user details using user email
 * @author Ankit choubey 
 * @exception UserNotFoundException
 * @param String uemail
 * @return Users user
 */
 public Users  getUserDetails(String email);
 
 
 /**
 * This method is used to get the sorted  list of all the users using PagingAndSortingRepository and here parameter direction either can be equal to asc or desc 
 *  asc => Ascending
 *  desc => Descending
 *  By default values : direction= desc, pageno=0 , records(no of records)=10
 * Field could be any one of the users entity field
 * @param String field,String direction ,Integer pageno,Integer records 
 * @author Ankit choubey
 * @exception OperationFaliureException
 * @return List<Users>
 * 
 * 
 */
 public List<Users> getAllSortedwithFieldUsers(String field,String direction,Integer pageno,Integer records);
 
 
 /**
  * This method is used to change the status of the user 
  * @author Ankit Choubey
  * @exception UserNotFoundException
  * @param String email,String status
  * @return Users 
  */
 public Users changeStatus(String email,String status);
 
 
 /**
  * This method is used by user to deactivate account 
  * @author Ankit Choubey
  * @exception UserNotLoggedException
  * @param String email
  * @return String 
  */
 public String deactivateAccount(String email);
 
 
 
 
 /**
  * This method is used to add address for one particular user
  * The following are address type- HOME,OFFICE,OTHER  ,any other  address type can lead to operation faliure exception
  * @author Ankit Choubey
  * @exception UserNotFoundException OperationFaliureException
  * @param String email,Address ob,String addressType
  * @return Address 
  */
 public Address addAddress(String email, Address ob,String addressType);
 
 
 /**
  * This method is used to get the list all addresses using user email and FirstName
  * @author Ankit Choubey
  * @exception UserNotFoundException
  * @param String email,String FirstName
  * @return Returns List of Address(List<Address>)
  */
 public List<Address>  getAllAddress(String email,String FirstName);
 
 
}
