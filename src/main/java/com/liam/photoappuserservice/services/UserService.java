package com.liam.photoappuserservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;

//import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.shared.UserDTO;


public interface UserService extends UserDetailsService{
	
	public UserDTO createUser(UserDTO newUser);

}
