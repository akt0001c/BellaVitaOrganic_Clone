package com.bellavita.config;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidatorFilter  extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt= request.getHeader(SecurityConstrants.JWT_HEADER);
		
		if(jwt!=null)
		{
			try {
				jwt= jwt.substring(7);
				
				SecretKey key= Keys.hmacShaKeyFor(SecurityConstrants.JWT_KEY.getBytes());
				Claims claim= Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
				
				String username= String.valueOf(claim.get("username"));
				String authorities= String.valueOf(claim.get("authorities"));
				
				Authentication auth= new UsernamePasswordAuthenticationToken(username,null,AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}
			 catch(Exception e) {
				 throw new BadCredentialsException("Invalid token received...");
			 }
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
	  return request.getServletPath().equals("/users/signIn");
		
		
	}

}
