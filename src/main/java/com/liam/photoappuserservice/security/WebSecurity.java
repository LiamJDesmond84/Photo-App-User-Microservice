package com.liam.photoappuserservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		
		
		httpSec.csrf().disable();
//		httpSec.authorizeHttpRequests().antMatchers("/api/users/**").permitAll();
//		httpSec.authorizeRequests().antMatchers("/api/users/**").permitAll();
		
		// Only allows requests from certain IP addresses(API-Gatway in this example)
		httpSec.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"));
		
		// Allows H-2 Console.
		httpSec.headers().frameOptions().disable();
		
	}

}
