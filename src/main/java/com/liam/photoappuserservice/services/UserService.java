package com.liam.photoappuserservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.models.User;
import com.liam.photoappuserservice.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	public User createUser(User newUser) {
		return userRepo.save(newUser);
	}

}
