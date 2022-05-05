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
//		httpSec.authorizeHttpRequests().antMatchers("/api/users/**").permitAll();
//		httpSec.authorizeRequests().antMatchers("/api/users/**").permitAll();
		
		// Only allows requests from certain IP addresses(API-Gatway in this example)
		httpSec.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
		.and()
		// Registering our custom AuthenticationFilter(same package) with below method
		.addFilter(getAuthenticationFilter());
		
		// Allows H-2 Console.
		httpSec.headers().frameOptions().disable();
		
	}

	// custom AuthenticationFilter from same package
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userServ, env, authenticationManager());
		// Still needs USER-SERVICE???  I don't think so.
		authenticationFilter.setFilterProcessesUrl("/api/users/login");
			// authenticationManager comes from Spring Security and we used getAuthenticationManager to call it in one of our AuthenticationFilter methods to use it here
		
		// Now in new AuthenticationFilter line above
//		authenticationFilter.setAuthenticationManager(authenticationManager());
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServ).passwordEncoder(bCrypt);
	}

}
