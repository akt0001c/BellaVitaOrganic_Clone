package com.bellavita.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthoritiesLoggingAfterFilter extends OncePerRequestFilter {
	
	private final Logger log = LoggerFactory.getLogger(AuthoritiesLoggingAfterFilter.class);

	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null)
		{
			log.info(" User "+auth.getName()+"  is successfully authenticated as "+auth.getAuthorities().toString()+" ");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
