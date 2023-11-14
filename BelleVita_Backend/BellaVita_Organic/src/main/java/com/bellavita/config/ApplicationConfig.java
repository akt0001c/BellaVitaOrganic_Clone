package com.bellavita.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bellavita.serviceImpl.UsersUserDetailService;

@Configuration
public class ApplicationConfig {
	
	
	private final UsersUserDetailService userDetailService;

	@Autowired
    public ApplicationConfig(UsersUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain mysecurityFilterChainHandler( HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers(HttpMethod.POST,"/signUp").permitAll()
			.requestMatchers(HttpMethod.GET,"/products").permitAll()
			.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
			.anyRequest().authenticated();
		}).csrf(csrf->csrf.disable())
		  .formLogin(Customizer.withDefaults())
		  .httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
	
//	@Bean
//	public UserDetailsService getInMemoryUserDetailsManager() {
//		InMemoryUserDetailsManager userServiceManager = new InMemoryUserDetailsManager();
//		 UserDetails user1= User.withUsername("ankit").password(getPasswordEncoder().encode("12345")).authorities("ROLE_ADMIN").build();
//		 
//		 userServiceManager.createUser(user1);
//		
//		return userServiceManager;
//		
//	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
	
}
