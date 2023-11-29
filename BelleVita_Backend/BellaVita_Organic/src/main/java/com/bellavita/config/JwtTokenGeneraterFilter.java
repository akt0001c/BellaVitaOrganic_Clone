package com.bellavita.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtTokenGeneraterFilter  extends OncePerRequestFilter{
	
	private Logger logger = LoggerFactory.getLogger(JwtTokenGeneraterFilter.class);
	
	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritySet = new HashSet<>();
		for(GrantedAuthority authority :collection)
		{
			authoritySet.add(authority.getAuthority());
		}
		
		return String.join(",", authoritySet);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null)
		{
			logger.info("Jwt token generation is in process");
			
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstrants.JWT_KEY.getBytes());
			
			
			String jwt = Jwts.builder()
						  .issuer("ankit")
					      .subject("jwt token")
					      .claim("username", auth.getName())
					      .claim("authorities",populateAuthorities(auth.getAuthorities()))
					      .expiration(new Date(new Date().getTime()+360000))
					      .signWith(key).compact();
			
			response.setHeader(SecurityConstrants.JWT_HEADER, jwt);
			logger.info("Jwt token generated");
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}
	

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
	  return !request.getServletPath().equals("/users/signIn");
		
		
	}

}
