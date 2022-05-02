package com.liam.photoappuserservice.services;

import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.shared.UserDTO;

@Service
public interface UserService {
	
	public UserDTO createUser(UserDTO newUser);

}
