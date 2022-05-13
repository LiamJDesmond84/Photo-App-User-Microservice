package com.liam.photoappuserservice.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.liam.photoappuserservice.services.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		
		

		
		httpSec.csrf().disable();
		// ALLOW requests from these URLs
//		httpSec.authorizeHttpRequests().antMatchers("/api/users/**").permitAll();
//		httpSec.authorizeRequests().antMatchers("/api/users/**").permitAll();
		
		// ONLY ALLOWS requests from certain IP addresses(API-Gatway in this example)
		httpSec.authorizeRequests()
		.antMatchers("/USER-SERVICE/actuator/**").permitAll() // Not working - Maybe now?
		.antMatchers("/api/users/actuator/**").permitAll() // Not working
		.antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
		.antMatchers("/h2-console/**").permitAll()
		.and()
		
		// Registering our custom AuthenticationFilter(same package) with getAuthenticationFilter(){} method below
		.addFilter(getAuthenticationFilter());
		
		// Allows H-2 Console.
		httpSec.headers().frameOptions().disable();
		
	}

	// custom AuthenticationFilter from same package
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userServ, env, authenticationManager());
		
		// Still needs USER-SERVICE???  I don't think so.
		// Custom User Authentication URL - Dude says to set it in the application.properties(yml) file & not hardcode it.  But this is fine for now.
		authenticationFilter.setFilterProcessesUrl("/api/users/login");

		
		//--- Now in new AuthenticationFilter line above
			// authenticationManager comes from Spring Security and we used getAuthenticationManager to call it in one of our AuthenticationFilter methods to use it here
//			authenticationFilter.setAuthenticationManager(authenticationManager());
		//---
		
		
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServ).passwordEncoder(bCrypt);
	}

}
