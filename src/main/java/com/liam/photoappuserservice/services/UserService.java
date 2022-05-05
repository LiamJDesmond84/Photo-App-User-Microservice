package com.liam.photoappuserservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;

//import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.shared.UserDTO;

// UserDetailsService comes from Spring Framework & is used in WebSecurity class
public interface UserService extends UserDetailsService{
	
	public UserDTO createUser(UserDTO newUser);

	public UserDTO getUserDetailsByEmail(String email);
	
}
