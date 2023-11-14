package com.bellavita.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bellavita.entity.Users;
import com.bellavita.repository.UsersRepository;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	private UsersRepository urepo;
	private PasswordEncoder pencoder;
	
	@Autowired
	public void setUrepo(UsersRepository urepo) {
		this.urepo = urepo;
	}

	@Autowired
	public void setPencoder(PasswordEncoder pencoder) {
		this.pencoder = pencoder;
	}

	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("This is my custome authentication provider");
		String username= authentication.getName();
		String password= authentication.getCredentials().toString();
		
		Optional<Users> opt= urepo.findByEmail(username);
		
		if(opt.isEmpty())
		{
			throw new BadCredentialsException("No User registerd with this details");
		}
		else
		{
			Users user = opt.get();
			if(pencoder.matches(password, user.getPassword()))
					{
				      List<GrantedAuthority> authorities = new ArrayList<>();
				      return new UsernamePasswordAuthenticationToken(username,password,authorities);
					}
			else {
				throw new BadCredentialsException("Invalid Password");
			}
		}
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
		
	}

}
