package com.vitaOrganic.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vitaOrganic.entity.Users;
import com.vitaOrganic.repository.UsersRepository;

@Service
public class UsersUserDetailService implements UserDetailsService {
	
	private UsersRepository urepo;

	@Autowired
	public void setUrepo(UsersRepository urepo) {
		this.urepo = urepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> ob= urepo.findByEmail(username);
		if(ob.isPresent()) {
			Users user= ob.get();
			
			//this is the list of authorities
			List<GrantedAuthority> authorities = new ArrayList<>();
			//authorities.add(new SimpleGrantedAuthority(user.getRole()));
			
			//here User object will be returned as an implementation of UserDetails interface
			return new User(user.getEmail(),user.getPassword(),authorities);
		}
		else {
			 throw new BadCredentialsException("Not user found or incorrect email :"+username);
		}
		
		
	}

	

}
