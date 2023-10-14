package com.vitaOrganic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomerCofiguration {
 
	@Bean		
	public SecurityFilterChain mySecurityConfig(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests((auth)->{
				auth.requestMatchers("/signUp","/products").permitAll()
				.anyRequest().authenticated();
		       }).csrf(csrf->csrf.disable())
		         .formLogin(Customizer.withDefaults())
		         .httpBasic(Customizer.withDefaults());
		
		
		return http.build();
	} 
	
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		UserDetails admin= User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("1234")
//				.build();
//		
//		UserDetails user= User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("1234")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
//	}
	
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//		UserDetails admin= User.withUsername("admim").password("1234").authorities("admin").build();
//		UserDetails user= User.withUsername("user").password("1234").authorities("read").build();
//		 userDetailsService.createUser(admin);
//		 userDetailsService.createUser(user);
//		 
//		 return userDetailsService;
//	}
	
//	
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
}
