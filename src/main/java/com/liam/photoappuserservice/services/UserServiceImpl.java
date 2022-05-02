package com.liam.photoappuserservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.repositories.UserRepository;
import com.liam.photoappuserservice.shared.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	public UserServiceImpl() {}
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO newUser) {
		// TODO Auto-generated method stub
		return userRepo.save(newUser);
	}

}
