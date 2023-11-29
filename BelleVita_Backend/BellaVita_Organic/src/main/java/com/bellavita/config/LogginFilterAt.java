package com.bellavita.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogginFilterAt extends OncePerRequestFilter {
	
	private Logger log = LoggerFactory.getLogger(LogginFilterAt.class);



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
        log.info("Authentication is in progress");
		
		filterChain.doFilter(request, response);
	}

}
