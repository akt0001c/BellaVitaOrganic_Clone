package com.bellavita.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bellavita.entity.Users;
import com.bellavita.entity.UsersUserDetail;
import com.bellavita.repository.UsersRepository;

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
			//List<GrantedAuthority> authorities = new ArrayList<>();
			//authorities.add(new SimpleGrantedAuthority(user.getRole()));
			
			//here User object will be returned as an implementation of UserDetails interface
			//return new User(user.getEmail(),user.getPassword(),authorities);
			
			return new UsersUserDetail(user);
		}
		else {
			 throw new BadCredentialsException("Not user found or incorrect email :"+username);
		}
		
		
	}

	
}

