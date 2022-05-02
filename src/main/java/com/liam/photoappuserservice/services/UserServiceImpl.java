package com.liam.photoappuserservice.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.liam.photoappuserservice.repositories.UserRepository;
import com.liam.photoappuserservice.shared.UserDTO;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO newUser) {
		// TODO Auto-generated method stub
		return userRepo.save(newUser);
	}

}
