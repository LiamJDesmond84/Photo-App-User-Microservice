package com.liam.photoappuserservice.services;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.liam.photoappuserservice.models.User;
import com.liam.photoappuserservice.repositories.UserRepository;
import com.liam.photoappuserservice.shared.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	BCryptPasswordEncoder bCrypt;
	
	public UserServiceImpl() {}
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO newUser) {
		User user = new User();
		user = UserMapper.INSTANCE.destinationToSource(newUser);
		
		// Encrypting password
		user.setPassword(bCrypt.encode(user.getPassword()));


//		System.out.println(ReflectionToStringBuilder.toString(userDTO));
		User createdUser = userRepo.save(user);

//		UserDTO user = UserMapper.INSTANCE.destinationToSource(userDTO);
		UserDTO userDTO = UserMapper.INSTANCE.sourceToDestination(createdUser);
		System.out.println(ReflectionToStringBuilder.toString(userDTO));

		return userDTO;
	}

}
