package com.vitaOrganic.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ApplicationConfiguration {
 
	@Bean		
	public SecurityFilterChain mySecurityConfig(HttpSecurity http)throws Exception{
		http.cors(cors->{
			cors.configurationSource(new CorsConfigurationSource() {
				
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					CorsConfiguration cfg = new CorsConfiguration();
					cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
					cfg.setAllowedMethods(Collections.singletonList("*"));
					cfg.setAllowCredentials(true);
					cfg.setAllowedHeaders(Collections.singletonList("*"));
					cfg.setExposedHeaders(Arrays.asList("Authorization"));
				   return cfg;
				}
			});
		})
		.authorizeHttpRequests((auth)->{
				auth.requestMatchers("/signUp","/products").permitAll()
				.anyRequest().authenticated();
		       }).csrf(csrf->csrf.disable())
		         .formLogin(Customizer.withDefaults())
		         .httpBasic(Customizer.withDefaults());
		
		
		return http.build();
	} 
	
	

	
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails admin= User.withUsername("admim").password("1234").authorities("admin").build();
		
		 userDetailsService.createUser(admin);
		
		 
		 return userDetailsService;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
