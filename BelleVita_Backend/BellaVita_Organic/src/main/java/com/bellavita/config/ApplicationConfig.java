package com.bellavita.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bellavita.serviceImpl.UsersUserDetailService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ApplicationConfig {
	
	
	private final UsersUserDetailService userDetailService;


    public UsersUserDetailService getUserDetailService() {
		return userDetailService;
	}

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
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		
		http
		
		 .sessionManagement(sessionManagement-> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		
		
		.cors(cors->{
			cors.configurationSource(new CorsConfigurationSource() {

				@Override
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
		
		
		
		
		
		
		.authorizeHttpRequests(auth->{
			auth.requestMatchers(HttpMethod.POST,"/users/signUp").permitAll()
		     .requestMatchers(HttpMethod.GET,"/products/products").permitAll()
			.requestMatchers("/products/**","/transactions/**","/transactionMethods/**").hasRole("ADMIN")
			.requestMatchers("users/user/{uemail}","users/user/status","users/addresses","orders/update/{oid}","orders/orders").hasRole("ADMIN")
			.requestMatchers("/users/add/address","/transactions/user/logged/transactions").hasAnyRole("ADMIN","USER")
			.requestMatchers("orders/placeOrder","orders/order/{oid}","orders/orders/logged/user").hasAnyRole("ADMIN","USER")
			.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
			
			.anyRequest().authenticated();
		})
		.csrf(csrf->csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("users/signUp").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		  .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		  .addFilterAfter(new CsrfCookieFilter(),BasicAuthenticationFilter.class)
		  .addFilterAfter(new JwtTokenGeneraterFilter(),CsrfCookieFilter.class )
		  .addFilterAfter(new AuthoritiesLoggingAfterFilter(), JwtTokenGeneraterFilter.class)
		  .addFilterAt(new LogginFilterAt(), BasicAuthenticationFilter.class)
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
