package com.liam.photoappuserservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		
		httpSec.csrf().disable();
		httpSec.authorizeHttpRequests().antMatchers("/api/users/**").permitAll();
		
	}

}
