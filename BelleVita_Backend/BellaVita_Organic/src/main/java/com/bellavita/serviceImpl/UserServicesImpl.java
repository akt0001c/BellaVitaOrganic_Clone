package com.bellavita.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bellavita.entity.Address;
import com.bellavita.entity.AddressType;
import com.bellavita.entity.UserStatus;
import com.bellavita.entity.Users;
import com.bellavita.exceptions.OperationFaliureException;
import com.bellavita.exceptions.UserAlreadyExistException;
import com.bellavita.exceptions.UserNotFoundException;
import com.bellavita.exceptions.UserNotLoggedInException;
import com.bellavita.repository.UsersRepository;
import com.bellavita.service.UsersServices;

@Service
public class UserServicesImpl implements UsersServices {
	
	private UsersRepository urepo;

	@Autowired
	public void setUrepo(UsersRepository urepo) {
		this.urepo = urepo;
	}

	

	/**
	 * It used by user to create an account as a user or sign up
	 * @exception OperationFaliureException 
	 * @param Users object which will be provided by user
	 * @author Ankit Choubey
	 * @return Users object
	 */
	@Override
	public Users userSignUp(Users user) {
		
		Optional<Users> ob= urepo.findByEmail(user.getEmail());
		if(ob.isPresent())
			   throw new UserAlreadyExistException("Email already exist ,Please signup with another email");
		
		
		Users res=null;
		
		user.setCreatedAt(LocalDateTime.now());
		user.setRole("ROLE_USER");
		user.setStatus(UserStatus.Active);
		
		try {
			res= urepo.save(user);
		
		  if(res==null)
			throw new Exception();
		}catch(Exception e) {
			throw new OperationFaliureException("Something went wrong ,or Operation faliure");
		}
			
		return res;
	}

	/**
	 * This mehtod is user to get user details using user email
	 * @author Ankit choubey 
	 * @exception UserNotFoundException
	 * @param String uemail
	 * @return Users user
	 */
	@Override
	public Users getUserDetails(String email) {
		 Users user= urepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User does not exist with this email : "+email));
		 
		return  user;
	}

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
	@Override
	public List<Users> getAllSortedwithFieldUsers(String field ,String direction ,Integer pageno,Integer records) {
		Sort sort=null;
		
		if(field==null)
			 field= "createdAt";
		
		if(pageno==null)
			  pageno=0;
		
		if(records==null)
			  records= 10;
		
		sort= direction.equalsIgnoreCase("asc")?Sort.by(field).ascending():Sort.by(field).descending();
		
		Pageable p= PageRequest.of(pageno, records, sort);
		Page<Users> page = urepo.findAll(p);
		
		List<Users>  res = page.getContent();
		if(res.isEmpty())
			  throw new OperationFaliureException("NO user found");
		
		return res;
	}

	/**
	 * This method is used to change the status of the user 
	 * @author Ankit Choubey
	 * @exception UserNotFoundException
	 * @param String email,String status
	 * @return Users 
	 */
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

	/**
	 * This method is used by user to deactivate account 
	 * @author Ankit Choubey
	 * @exception UserNotLoggedException
	 * @param String email
	 * @return String 
	 */
	@Override
	public String deactivateAccount(String email) {
		String res=null;
		Users user= urepo.findByEmail(email).orElseThrow(()-> new UserNotLoggedInException("User not logged in,please login first"));
		user.setStatus(UserStatus.Deactivated_by_user);
		 
		if(user.getStatus()== UserStatus.Deactivated_by_user)
			   res= "User account has been deactivated";
		return res;
	}
	

	/**
	 * This method is used to get the list all addresses using user email and FirstName
	 * @author Ankit Choubey
	 * @exception UserNotFoundException
	 * @param String email,String FirstName
	 * @return Users 
	 */
	@Override
	public List<Address> getAllAddress(String email, String FirstName ) {
		
		List<Address>  addressList = null;
		
		Users user= urepo.findByEmailAndFirstName(email, FirstName).orElseThrow( ()-> new  UserNotFoundException("User does not exist with this email : "+email));
		addressList= user.getAddresses().stream().toList();
		return addressList;
	}



	/**
	 * This method is used to create an account for an admin only
	 * @author Ankit Choubey
	 * @exception OperationFaliureException
	 * @param Users  user
	 * @return Users 
	 */
	@Override
	public Users adminSignUp(Users user) {
		Optional<Users> ob= urepo.findByEmail(user.getEmail());
		if(ob.isPresent())
			   throw new UserAlreadyExistException("Email already exist ,Please signup with another email");
		System.out.println("Welcome in admin registration");
		Users res=null;
		user.setCreatedAt(LocalDateTime.now());
		user.setRole("ROLE_ADMIN");
		user.setStatus(UserStatus.Active);
		
		try {
			res= urepo.save(user);
		
		
		if(res==null)
		   	throw new Exception();
		
		
		}catch(Exception e) {
			throw new OperationFaliureException("Something went wrong ,or Operation faliure");
		}
			
		return res;
	
	}



	/**
	 * This method is used to add address for one particular user
	 * The following are address type- HOME,OFFICE,OTHER  ,any other  address type can lead to operation faliure exception
	 * @author Ankit Choubey
	 * @exception UserNotFoundException OperationFaliureException
	 * @param String email,Address ob,String addressType
	 * @return Address 
	 */
	@Override
	public Address addAddress(String email, Address ob,String adressType) {
		
		
		Users user= urepo.findByEmail(email).orElseThrow( ()-> new  UserNotFoundException("User does not exist with this email : "+email));
		ob.setUpdatedAt(LocalDateTime.now());
		try {
		ob.setAdType(AddressType.valueOf(AddressType.class, adressType));
		}catch(Exception exp) {
			throw new OperationFaliureException("Something went wrong ,please try again");
		}
		user.getAddresses().add(ob);
		urepo.save(user);
		return ob;
	}

}
