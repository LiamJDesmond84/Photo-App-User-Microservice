package com.liam.photoappuserservice.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liam.photoappuserservice.models.UserLogin;
import com.liam.photoappuserservice.services.UserService;
import com.liam.photoappuserservice.shared.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	

	private UserService userServ;
	

	private Environment env;
	
	// Has to done this way I think, constructor needs to be recognized with these paramaters in WebSecurity
	public AuthenticationFilter(UserService userServ, Environment env, AuthenticationManager authenticationManager) {
		this.userServ = userServ;
		this.env = env;
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		 
		 try {
			 UserLogin creds = new ObjectMapper()
					 .readValue(request.getInputStream(), UserLogin.class);
			 
			 return getAuthenticationManager()
					 .authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		 }
		 catch (IOException e) {
			throw new RuntimeException(e);
		}
	 }
	
	// Generate JWT Token and add it to HTTP response header after successful login for re-use within the application
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		UserDTO userDTO = userServ.getUserDetailsByEmail(userName);
		
		String token = Jwts.builder()
				// setSubject can be any String I think.  We change ID to String here.  So maybe change it back at some point.
				.setSubject(userDTO.getId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
				.compact();
		System.out.println(token);
		response.addHeader("token", token);
		response.addHeader("id", userDTO.getId());
		
	}

}
